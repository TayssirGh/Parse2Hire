#!/bin/bash


docker-compose up -d configsvr


docker-compose up -d shard1 shard2 shard3 shard4


docker-compose up -d mongos


echo "Waiting for MongoDB containers to start..."
sleep 10


echo "Initializing replica sets..."
docker exec -it configsvr mongosh --port 27020 --eval "rs.initiate({ _id: 'configReplSet', members: [{ _id: 0, host: 'configsvr:27020' }]})"
docker exec -it shard1 mongosh --port 27018 --eval "rs.initiate({ _id: 'shard1ReplSet', members: [{ _id: 0, host: 'shard1:27018' }]})"
docker exec -it shard2 mongosh --port 27019 --eval "rs.initiate({ _id: 'shard2ReplSet', members: [{ _id: 0, host: 'shard2:27019' }]})"
docker exec -it shard3 mongosh --port 27021 --eval "rs.initiate({ _id: 'shard3ReplSet', members: [{ _id: 0, host: 'shard3:27021' }]})"
docker exec -it shard4 mongosh --port 27022 --eval "rs.initiate({ _id: 'shard4ReplSet', members: [{ _id: 0, host: 'shard4:27022' }]})"


sleep 10


echo "Adding shard servers to the cluster..."
docker exec -it mongos mongosh --port 27023 --eval "sh.addShard('shard1ReplSet/shard1:27018')"
docker exec -it mongos mongosh --port 27023 --eval "sh.addShard('shard2ReplSet/shard2:27019')"
docker exec -it mongos mongosh --port 27023 --eval "sh.addShard('shard3ReplSet/shard3:27021')"
docker exec -it mongos mongosh --port 27023 --eval "sh.addShard('shard4ReplSet/shard4:27022')"


echo "Enabling sharding for candidatesDB..."
docker exec -it mongos mongosh --port 27023 --eval 'sh.enableSharding("candidatesDB")'

# Shard the Collection on the "interviewScore" Field
echo "Sharding the candidates collection on interviewScore..."
docker exec -it mongos mongosh --port 27023 --eval 'sh.shardCollection("candidatesDB.candidates", { "interviewScore": 1 })'

# Tag Shards for Range-Based Sharding
echo "Defining shard tags and range boundaries..."
docker exec -it mongos mongosh --port 27023 --eval 'sh.addShardTag("shard1ReplSet", "range1")'
docker exec -it mongos mongosh --port 27023 --eval 'sh.addShardTag("shard2ReplSet", "range2")'

echo "Associating range boundaries with shard tags..."
docker exec -it mongos mongosh --port 27023 --eval 'sh.addTagRange("candidatesDB.candidates", { "interviewScore": MinKey }, { "interviewScore": 50 }, "range1")'
docker exec -it mongos mongosh --port 27023 --eval 'sh.addTagRange("candidatesDB.candidates", { "interviewScore": 50 }, { "interviewScore": MaxKey }, "range2")'


echo "Defining shard tags for interview collection..."
docker exec -it mongos mongosh --port 27023 --eval 'sh.addShardTag("shard3ReplSet", "interview_range3")'
docker exec -it mongos mongosh --port 27023 --eval 'sh.addShardTag("shard4ReplSet", "interview_range4")'

docker exec -it mongos mongosh --port 27023 --eval 'sh.shardCollection("candidatesDB.interview", { interviewerName: 1, candidateId: 1 })
'

# Insert test data into the interview collection (example)
echo "Inserting test data into the interview collection..."
docker exec -it mongos mongosh --port 27023 --eval 'db.interview.insertMany([
  { "candidateId": "alice123", "companyName": "Company A", "interviewDate": ISODate("2025-02-10T10:00:00Z"), "interviewerName": "John", "createdAt": ISODate("2025-01-10T09:00:00Z") },
  { "candidateId": "bob456", "companyName": "Company B", "interviewDate": ISODate("2025-02-12T14:00:00Z"), "interviewerName": "Jane", "createdAt": ISODate("2025-01-15T09:30:00Z") },
  { "candidateId": "alice123", "companyName": "Company B", "interviewDate": ISODate("2025-03-05T09:00:00Z"), "interviewerName": "James", "createdAt": ISODate("2025-01-20T10:00:00Z") },
  { "candidateId": "charlie789", "companyName": "Company A", "interviewDate": ISODate("2025-03-07T15:00:00Z"), "interviewerName": "David", "createdAt": ISODate("2025-01-25T11:00:00Z") }
])'


echo "Inserting test data into the candidates collection..."
docker exec -it mongos mongosh --port 27023 --eval 'db.candidates.insertMany([
  { name: "Alice", interviewScore: 60 },
  { name: "Bob", interviewScore: 45 },
  { name: "Charlie", interviewScore: 75 },
  { name: "David", interviewScore: 30 },
  { name: "Eve", interviewScore: 50 }
])'

echo "Sharding setup complete!"
