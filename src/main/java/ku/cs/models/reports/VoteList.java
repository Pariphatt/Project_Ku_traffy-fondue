package ku.cs.models.reports;

import java.util.ArrayList;

public class VoteList {
    private ArrayList<Vote> votes;
    public VoteList(){votes = new ArrayList<Vote>();}
    public void addVote(Vote vote){
        votes.add(vote);
    }
    public ArrayList<Vote> getaAllVote(){return votes;}

    public boolean isExistTopic(String topic) {
        for(Vote vote: votes) {
            if(vote.getTopic().equals(topic)){
                return true;
            }
        }
        return false;
    }

    public boolean isExistUserReport(String userReport) {
        for(Vote vote: votes) {
            if(vote.getUserReport().equals(userReport)){
                return true;
            }
        }
        return false;
    }

    public boolean isExistEverVote(String everVote){
        for (Vote vote: votes) {
            if (vote.getTopicAndUserReport().equals(everVote)){
                return true;
            }
        } return false;
    }


}
