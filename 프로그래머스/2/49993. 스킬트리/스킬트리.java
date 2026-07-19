class Solution {
    static int N;
    static int lenS;
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        N = skill_trees.length;
        for(int i = 0;i<N;i++){
            String tree = skill_trees[i];
            String tmp = "";
            for(int j = 0;j<tree.length();j++){
                char c = tree.charAt(j);
                if(skill.contains(c+"")){
                    tmp += c+"";
                }
            }
            // tree[i] = tmp;
            if(skill.startsWith(tmp)){
                answer++;
            }
        }
        
        
        return answer;
    }
}