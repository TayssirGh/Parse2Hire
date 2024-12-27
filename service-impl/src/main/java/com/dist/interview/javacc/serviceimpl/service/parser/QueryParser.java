package com.dist.interview.javacc.serviceimpl.service.parser;/* QueryParser.java */
/* Generated By:JavaCC: Do not edit this line. QueryParser.java */
import java.util.ArrayList;
import java.util.List;

import com.dist.interview.javacc.infra.model.Condition;
import com.dist.interview.javacc.infra.model.LogicalOperator;
import com.dist.interview.javacc.infra.model.Operator;
import com.dist.interview.javacc.infra.model.ParsedQuery;

public class QueryParser implements QueryParserConstants {
    public static void main(String[] args) throws ParseException {
        System.out.println("parse to hire :");
        QueryParser parser = new QueryParser(System.in);
        ParsedQuery query = parser.statement();
        System.out.println("Parsed Query: " + query);
    }

  static final public ParsedQuery statement() throws ParseException {ParsedQuery parsedQuery = new ParsedQuery();
    String templateName;
    List<Condition> conditions = new ArrayList<>();
    jj_consume_token(LBRACE);
    templateName = useClause();
    forClause();
    conditions = whereClause();
    jj_consume_token(RBRACE);
parsedQuery.setTemplateName(templateName);
        parsedQuery.setConditions(conditions);
        {if ("" != null) return parsedQuery;}
    throw new Error("Missing return statement in function");
}

  static final public String useClause() throws ParseException {Token t;
    jj_consume_token(USE);
    jj_consume_token(TEMPLATE);
    t = jj_consume_token(STRING);
{if ("" != null) return t.image.substring(1, t.image.length() - 1);} // Remove quotes

    throw new Error("Missing return statement in function");
}

  static final public void forClause() throws ParseException {
    jj_consume_token(FOR);
    jj_consume_token(ALL);
    jj_consume_token(CANDIDATES);
}

  static final public List<Condition> whereClause() throws ParseException {Condition condition;
    List<Condition> conditions = new ArrayList<>();
    jj_consume_token(WHERE);
    condition = condition();
conditions.add(condition);
        {if ("" != null) return conditions;}
    throw new Error("Missing return statement in function");
}

  static final public Condition condition() throws ParseException {Condition left = null;
    LogicalOperator logicalOperator = null;
    Condition right = null;
    left = simpleCondition();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND:
    case OR:{
      logicalOperator = logicalOperator();
      right = condition();
{if ("" != null) return new Condition(left, logicalOperator, right, false);}
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  static final public Condition simpleCondition() throws ParseException {String leftOperand = null, rightOperand = null;
    Operator operator = null;
    Token tLeftOperand, tRightOperand;
    Condition nestedCondition = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:{
      jj_consume_token(LPAREN);
      nestedCondition = condition();
      jj_consume_token(RPAREN);
{if ("" != null) return nestedCondition;}
      break;
      }
    case IDENTIFIER:{
      tLeftOperand = jj_consume_token(IDENTIFIER);
      operator = operator();
      tRightOperand = jj_consume_token(NUMBER);
leftOperand = tLeftOperand.image;
    rightOperand = tRightOperand.image;
    {if ("" != null) return new Condition(leftOperand, operator, rightOperand);}

    {if (true) throw new ParseException("Invalid simple condition format");}
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  static final public Operator operator() throws ParseException {Operator op = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQ:{
      jj_consume_token(EQ);
op = Operator.EQ;
      break;
      }
    case GTE:{
      jj_consume_token(GTE);
op = Operator.GTE;
      break;
      }
    case LTE:{
      jj_consume_token(LTE);
op = Operator.LTE;
      break;
      }
    case LT:{
      jj_consume_token(LT);
op = Operator.LT;
      break;
      }
    case GT:{
      jj_consume_token(GT);
op = Operator.GT;
      break;
      }
    case MATCH:{
      jj_consume_token(MATCH);
op = Operator.MATCH;
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return op;}
    throw new Error("Missing return statement in function");
}

  static final public LogicalOperator logicalOperator() throws ParseException {LogicalOperator op = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND:{
      jj_consume_token(AND);
op = LogicalOperator.AND;
      break;
      }
    case OR:{
      jj_consume_token(OR);
op = LogicalOperator.OR;
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return op;}
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public QueryParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[4];
  static private int[] jj_la1_0;
  static {
	   jj_la1_init_0();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x18000,0x80080,0x3e20000,0x18000,};
	}

  /** Constructor with InputStream. */
  public QueryParser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public QueryParser(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new QueryParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public QueryParser(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new QueryParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new QueryParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public QueryParser(QueryParserTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(QueryParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[26];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 4; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 26; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
