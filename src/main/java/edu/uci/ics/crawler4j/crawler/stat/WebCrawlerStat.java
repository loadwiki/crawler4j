package edu.uci.ics.crawler4j.crawler.stat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class WebCrawlerStat {
	
	  private static final Logger logger = LoggerFactory.getLogger(WebCrawlerStat.class);
	
	  private int id;
	  
	  private int totalProcessedPages = 0;
	  private long totalLinks = 0;
	  private long totalTextSize = 0;
	  private HashMap<String,Integer>	linkTypeTable = new HashMap<String,Integer>();
	  
	  private long startTime = System.currentTimeMillis();
	  private long networktime;
	  private long retrytime;
	  
	  private HashMap<Integer,Integer>	httpResultCode = new HashMap<Integer,Integer>();
	  
	  public void start()
	  {
		  startTime = System.currentTimeMillis();
	  }
	  public void setID(int id)
	  {
		  this.id = id;
	  }
	  
	  public long getExecTime()
	  {
		  return (System.currentTimeMillis()-startTime)/1000;
	  }
	  
	  public int getTotalProcessedPages() {
	    return totalProcessedPages;
	  }

	  public void setTotalProcessedPages(int totalProcessedPages) {
	    this.totalProcessedPages = totalProcessedPages;
	  }

	  public void incProcessedPages() {
	    this.totalProcessedPages++;
	  }

	  public long getTotalLinks() {
	    return totalLinks;
	  }

	  public void setTotalLinks(long totalLinks) {
	    this.totalLinks = totalLinks;
	  }

	  public long getTotalTextSize() {
	    return totalTextSize;
	  }

	  public void setTotalTextSize(long totalTextSize) {
	    this.totalTextSize = totalTextSize;
	  }

	  public void incTotalLinks(int count) {
	    this.totalLinks += count;
	  }

	  public void incTotalTextSize(int count) {
	    this.totalTextSize += count;
	  }
	  
	  public  void incLinkType(String linktype)
	  {
		  Integer count = linkTypeTable.get(linktype);
		  if(count != null)
		  {
			 count++;
			 linkTypeTable.put(linktype, count);
		  }
		  else
			  linkTypeTable.put(linktype,1);
	  }
	  public  void  pLinkTypeTable()
	  {
		  for(Map.Entry<String,Integer> entry :linkTypeTable.entrySet())
		  {
			  logger.info("\tcrawler {} link type{},total count{}",id,entry.getKey(),entry.getValue());
		  }
	  }
	  
	  public void incNetworkTime(long term)
	  {
		  networktime += term;
	  }
	  
	  public long getNetworkTime()
	  {
		  return networktime;
	  }
	  
	  
	  public void statResultCode(int resultcode)
	  {
		  Integer count = httpResultCode.get(resultcode);
		  if(count == null)
		  {
			  httpResultCode.put(resultcode, 1);
		  }
		  else
		  {
			  count++;
			  httpResultCode.put(resultcode,count);
		  }
	  }
	  public void pResultCode()
	  {
		  for(Map.Entry<Integer,Integer> entry:httpResultCode.entrySet())
		  {
			  logger.info("\tcrawler {} result code {},total count{}",id,entry.getKey(),entry.getValue());
		  }
	  }
	  public void pNetworkTime()
	  {
		  logger.info("\tcrawler {}  networktime {}",id,networktime/1000);
	  }
}
