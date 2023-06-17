package com.stephen.crawler;
public class SpiderTest {

    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {
        Spiderbot spider = new Spiderbot();
        //spider.search("http://arstechnica.com/", "computer");
        spider.search("http://geeksforgeeks.org/", "array");
    }
}
