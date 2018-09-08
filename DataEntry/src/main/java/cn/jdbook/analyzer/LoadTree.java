package cn.jdbook.analyzer;

public class LoadTree {

    private static class LoadTreeInstance{
      private static LoadTree instance =  new LoadTree();
    }
    private LoadTree(){

    }

    public static LoadTree getInstance(){
        return LoadTreeInstance.instance;
    }
}
