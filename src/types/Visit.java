package types;

import java.util.Date;

public class Visit {
    private final Prisoner visited;
    private final String name;
    private final Date date;
    private final String reason;
    public Visit(Prisoner visited, String name, Date date, String reason)
    {

        this.visited = visited;
        this.name = name;
        this.date = date;
        this.reason = reason;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Visit)
        {
            Visit v = (Visit)o;
            return v.visited.equals(visited) && v.name.equals(name) && v.date.equals(date) && v.reason.equals(reason);
        }
        return false;
    }
    public Prisoner getVisited()
    {
        return visited;
    }
    public String getName()
    {
        return name;
    }
    public Date getDate()
    {
        return date;
    }
    public String getReason()
    {
        return reason;
    }



}
