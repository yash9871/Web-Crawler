package com.stephen.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Element;

public class Spiderbot
{
  private static final int MAX_PAGES_TO_VISIT = 20;
  private Set<String> visitedPages = new HashSet<String>();
  private List<String> pagesToVisit = new LinkedList<String>();

  public void search(String url, String searchWord)
  {
      while(this.visitedPages.size() < MAX_PAGES_TO_VISIT)
      {
          String currUrl;
          SpiderLeg leg = new SpiderLeg();
          if(this.pagesToVisit.isEmpty())
          {
              currUrl = url;
              this.visitedPages.add(url);
          }
          else
          {
              currUrl = this.nextUrl();
          }
          leg.crawl(currUrl);
          
          boolean success = leg.searchForWord(searchWord);
          if(success)
          {
              System.out.println(String.format("Successful. Word %s found at %s", searchWord, currUrl));
              
          }
          this.pagesToVisit.addAll(leg.getLinks());
      }
      System.out.println("\nDone. Visited " + this.visitedPages.size() + "web pages(s)");
      
  }

  private String nextUrl()
  {
      String nextUrl;
      do
      {
          nextUrl = this.pagesToVisit.remove(0);
      } while(this.visitedPages.contains(nextUrl));
      this.visitedPages.add(nextUrl);
      return nextUrl;
  }
}
  