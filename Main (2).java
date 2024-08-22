import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<String, Boolean> voterRegistryMP = new HashMap<>();
    private static HashMap<String, Boolean> voterRegistryMLA = new HashMap<>();
    private static HashMap<String, Integer> candidatesMP = new HashMap<>();
    private static HashMap<String, Integer> candidatesMLA = new HashMap<>();

    public static void main(String[] args) {
        registerVoters();
        registerCandidates();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Voter ID:");
        String voterID = scanner.nextLine();

        if (isEligibleToVoteMP(voterID)) {
            System.out.println("Choose your MP candidate:");
            for (String candidate : candidatesMP.keySet()) {
                System.out.println(candidate);
            }
            String chosenCandidateMP = scanner.nextLine();

            if (candidatesMP.containsKey(chosenCandidateMP)) {
                castVoteMP(voterID, chosenCandidateMP);
                System.out.println("MP vote cast successfully!");
            } else {
                System.out.println("Invalid MP candidate selection.");
            }
        } else {
            System.out.println("You have already voted for MP or are not registered for MP voting.");
        }

        if (isEligibleToVoteMLA(voterID)) {
            System.out.println("Choose your MLA candidate:");
            for (String candidate : candidatesMLA.keySet()) {
                System.out.println(candidate);
            }
            String chosenCandidateMLA = scanner.nextLine();

            if (candidatesMLA.containsKey(chosenCandidateMLA)) {
                castVoteMLA(voterID, chosenCandidateMLA);
                System.out.println("MLA vote cast successfully!");
            } else {
                System.out.println("Invalid MLA candidate selection.");
            }
        } else {
            System.out.println("You have already voted for MLA or are not registered for MLA voting.");
        }

        scanner.close();
        displayResultsMP();
        announceWinnerMP();
        displayResultsMLA();
        announceWinnerMLA();
    }

    private static void registerVoters() {
        voterRegistryMP.put("100", false);
        voterRegistryMP.put("200", false);
        voterRegistryMP.put("300", false);
        voterRegistryMP.put("400", false);

        voterRegistryMLA.put("100", false);
        voterRegistryMLA.put("200", false);
        voterRegistryMLA.put("300", false);
        voterRegistryMLA.put("400", false);
    }

    private static void registerCandidates() {
        candidatesMP.put("Sharmila", 0);
        candidatesMP.put("YS.Jagan Mohan", 0);
        candidatesMP.put("Balakrishnaa", 0);
        candidatesMP.put("Deepak Das", 0);

        candidatesMLA.put("K.Atchannaidu", 0);
        candidatesMLA.put("Pawan Kalyan", 0);
        candidatesMLA.put("Bharat", 0);
        candidatesMLA.put("Amarnath", 0);
    }

    private static boolean isEligibleToVoteMP(String voterID) {
        return voterRegistryMP.containsKey(voterID) && !voterRegistryMP.get(voterID);
    }

    private static boolean isEligibleToVoteMLA(String voterID) {
        return voterRegistryMLA.containsKey(voterID) && !voterRegistryMLA.get(voterID);
    }

    private static void castVoteMP(String voterID, String candidate) {
        voterRegistryMP.put(voterID, true);
        candidatesMP.put(candidate, candidatesMP.get(candidate) + 1);
    }

    private static void castVoteMLA(String voterID, String candidate) {
        voterRegistryMLA.put(voterID, true);
        candidatesMLA.put(candidate, candidatesMLA.get(candidate) + 1);
    }

    private static void displayResultsMP() {
        System.out.println("MP Voting results:");
        for (String candidate : candidatesMP.keySet()) {
            System.out.println(candidate + ": " + candidatesMP.get(candidate) + " votes");
        }
    }

    private static void displayResultsMLA() {
        System.out.println("MLA Voting results:");
        for (String candidate : candidatesMLA.keySet()) {
            System.out.println(candidate + ": " + candidatesMLA.get(candidate) + " votes");
        }
    }

    private static void announceWinnerMP() {
        String winnerMP = null;
        int maxVotesMP = 0;
        boolean tieMP = false;

        for (String candidate : candidatesMP.keySet()) {
            int votes = candidatesMP.get(candidate);
            if (votes > maxVotesMP) {
                maxVotesMP = votes;
                winnerMP = candidate;
                tieMP = false;
            } else if (votes == maxVotesMP) {
                tieMP = true;
            }
        }

        if (tieMP) {
            System.out.println("The MP election is a tie.");
        } else {
            System.out.println("The MP winner is: " + winnerMP);
        }
    }

    private static void announceWinnerMLA() {
        String winnerMLA = null;
        int maxVotesMLA = 0;
        boolean tieMLA = false;

        for (String candidate : candidatesMLA.keySet()) {
            int votes = candidatesMLA.get(candidate);
            if (votes > maxVotesMLA) {
                maxVotesMLA = votes;
                winnerMLA = candidate;
                tieMLA = false;
            } else if (votes == maxVotesMLA) {
                tieMLA = true;
            }
        }

        if (tieMLA) {
            System.out.println("The MLA election is a tie.");
        } else {
            System.out.println("The MLA winner is: " + winnerMLA);
        }
    }
}
