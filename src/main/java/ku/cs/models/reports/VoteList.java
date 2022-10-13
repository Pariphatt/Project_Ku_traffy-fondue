package ku.cs.models.reports;

import ku.cs.services.Filterer;

import java.util.ArrayList;

public class VoteList {
    private ArrayList<Vote> votes;
    public VoteList(){votes = new ArrayList<Vote>();}
    public void addVote(Vote vote){
        votes.add(vote);
    }
    public ArrayList<Vote> getaAllVote(){return votes;}

}
