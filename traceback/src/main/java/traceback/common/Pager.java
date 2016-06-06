package traceback.common;

public class Pager 
{

    public Pager()
    {
        currentPageno = 1;
        totalRows = 1L;
        eachPageRows = 10;
        totalPages = 1L;
        toolBar = "";
        pageName = "";
        urlparams = "";
        formname = "";
        fromIndex = 0;
        eachPageRows = 10;
    }

    public int getCurrentPageno()
    {
        totalPages = getTotalPages();
        if((long)currentPageno > totalPages)
            currentPageno = Long.signum(totalPages);
        return currentPageno;
    }

    public int getCurrentPagenoDirect()
    {
        return currentPageno;
    }

    public void setCurrentPageno(int currentPageno)
    {
        if(currentPageno < 1)
            this.currentPageno = 1;
        else
            this.currentPageno = currentPageno;
    }

    public int getEachPageRows()
    {
        return eachPageRows;
    }

    public void setEachPageRows(int eachPageRows)
    {
        if(eachPageRows > 100)
            eachPageRows = 100;
        this.eachPageRows = eachPageRows;
    }

    public long getTotalRows()
    {
        return totalRows;
    }

    public void setTotalRows(long totalRows)
    {
        this.totalRows = totalRows;
    }

    public boolean isFirst()
    {
        return currentPageno <= 1;
    }

    public boolean isLast()
    {
        return (long)currentPageno >= totalPages;
    }

    public long getTotalPages()
    {
        if(eachPageRows < 1)
            eachPageRows = 10;
        long _totalpages;
        if(totalRows % (long)eachPageRows == 0L)
            _totalpages = totalRows / (long)eachPageRows;
        else
        if(totalRows % (long)eachPageRows > 0L)
            _totalpages = totalRows / (long)eachPageRows + 1L;
        else
            _totalpages = 1L;
        if(_totalpages == 0L)
            _totalpages = 1L;
        return _totalpages;
    }



   // private static boolean USED_IN_OUR_FRAMEWORK = true;
    static String pageNoParamFlagName = "pager.currentPageno";
    static String pageNoParamFlagID = "pager_currentPageno";
    protected int currentPageno;
    protected long totalRows;
    protected int eachPageRows;
    protected long totalPages;
    protected String toolBar;
    protected String pageName;
    protected String urlparams;
    protected String formname;
    protected int  fromIndex;
    
    public int getFromIndex()
    {
        return (currentPageno-1)*eachPageRows;
    }

    public void setFromIndex(int fromIndex)
    {
        this.fromIndex = fromIndex;
    }
    
    
    
}

