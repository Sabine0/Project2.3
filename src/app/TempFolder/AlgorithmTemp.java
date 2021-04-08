//package app.TempFolder;
//
//import app.games.gameobjects.OthelloBoard;
//
//public class AlgorithmTemp {
//
//    // test
//private int[] listOfCoordinates;
//    OthelloBoard othelloBoard = new OthelloBoard();
//
//
//// validmove check, char playing and char tegenstander moeten nog worden omgezet naar tiles.
//public boolean validMove(int c, int r, char playing, char tegenStander) {
//    boolean valid = false;
//    boolean p = true;
//    while(p == true){
//        if (othelloBoard.getTile(c-1, r-1) == tegenStander && checkUL(c-1, r-1, playing)) {
//            valid = true;
//        } else if (othelloBoard.getTile(c-1, r) == tegenStander && checkUM(c-1, r, playing)) {
//            valid = true;
//        } else if (othelloBoard.getTile(c-1, r+1) == tegenStander && checkUR(c-1, r+1, playing)) {
//            valid = true;
//        } else if (othelloBoard.getTile(c, r-1) == tegenStander && checkML(c, r-1, playing)) {
//            valid = true;
//        } else if (othelloBoard.getTile(c, r+1) == tegenStander && checkMR(c, r+1, playing)) {
//            valid = true;
//        } else if (othelloBoard.getTile(c+1, r-1) == tegenStander && checkDL(c+1, r-1, playing)) {
//            valid = true;
//        } else if (othelloBoard.getTile(c+1, r) == tegenStander && checkDM(c+1, r, playing)) {
//            valid = true;
//        } else if (othelloBoard.getTile(c+1, r+1) == tegenStander && checkDR(c+1, r+1, playing)) {
//            valid = true;
//        } else {
//            p =false;
//        }
//    }
//    return valid;
//}
//public boolean checkUL(int c, int r, char playing) {
//    int[] tempListOfCoordinates = new int[]{};
//    tempListOfCoordinates[0] = c+1;
//    tempListOfCoordinates[1] = r+1;
//    boolean valid = false;
//    int t = 2;
//    while(c > 0 && r > 0) {
//        tempListOfCoordinates[t] = c;
//        t++;
//        tempListOfCoordinates[t] = r;
//        t++;
//        c--;
//        r--;
//        if(othelloBoard.getTile(c, r) == playing) {
//            valid = true;
//            setArrayOfCoordinates(tempListOfCoordinates);
//            break;
//        }
//    }
//    return valid;
//}
//
//public boolean checkUM(int c, int r, char playing) {
//    int[] tempListOfCoordinates;
//    tempListOfCoordinates[0] = c+1;
//    tempListOfCoordinates[1] = r+1;
//    boolean valid = false;
//    int t = 2;
//    while(c>0) {
//        tempListOfCoordinates[t] = c;
//        t++;
//        tempListOfCoordinates[t] = r;
//        t++;
//        c--;
//        if(othelloBoard.getTile(c, r) == playing){
//            valid = true;
//            setArrayOfCoordinates(tempListOfCoordinates);
//            break;
//        }
//    }
//    return valid;
//}
//
//public boolean checkUR(int c, int r, char playing) {
//    int[] tempListOfCoordinates;
//    tempListOfCoordinates[0] = c+1;
//    tempListOfCoordinates[1] = r+1;
//    boolean valid = false;
//    int t = 2;
//    while(c>0 && r<7) {
//        tempListOfCoordinates[t] = c;
//        t++;
//        tempListOfCoordinates[t] = r;
//        t++;
//        c--;
//        r++;
//        if(othelloBoard.getTile(c, r) == playing){
//            valid = true;
//            setArrayOfCoordinates(tempListOfCoordinates);
//            break;
//        }
//    }
//    return valid;
//}
//
//public boolean checkML(int c, int r, char playing) {
//    int[] tempListOfCoordinates;
//    tempListOfCoordinates[0] = c+1;
//    tempListOfCoordinates[1] = r+1;
//    boolean valid = false;
//    int t = 2;
//    while(r>0) {
//        tempListOfCoordinates[t] = c;
//        t++;
//        tempListOfCoordinates[t] = r;
//        t++;
//        r--;
//        if(othelloBoard.getTile(c, r) == playing){
//            valid = true;
//            setArrayOfCoordinates(tempListOfCoordinates);
//            break;
//        }
//    }
//    return valid;
//}
//
//public boolean checkMR(int c, int r, char playing) {
//    int[] tempListOfCoordinates;
//    tempListOfCoordinates[0] = c+1;
//    tempListOfCoordinates[1] = r+1;
//    boolean valid = false;
//    int t = 2;
//    while(r<7) {
//        tempListOfCoordinates[t] = c;
//        t++;
//        tempListOfCoordinates[t] = r;
//        t++;
//        r++;
//        if(othelloBoard.getTile(c, r) == playing){
//            valid = true;
//            setArrayOfCoordinates(tempListOfCoordinates);
//            break;
//        }
//    }
//    return valid;
//}
//
//public boolean checkDL(int c, int r, char playing) {
//    int[] tempListOfCoordinates;
//    tempListOfCoordinates[0] = c+1;
//    tempListOfCoordinates[1] = r+1;
//    boolean valid = false;
//    int t = 2;
//    while(c<7 && r>0) {
//        tempListOfCoordinates[t] = c;
//        t++;
//        tempListOfCoordinates[t] = r;
//        t++;
//        c++;
//        r--;
//        if(othelloBoard.getTile(c, r) == playing){
//            valid = true;
//            setArrayOfCoordinates(tempListOfCoordinates);
//            break;
//        }
//    }
//    return valid;
//}
//
//public boolean checkDM(int c, int r, char playing) {
//    int[] tempListOfCoordinates;
//    tempListOfCoordinates[0] = c+1;
//    tempListOfCoordinates[1] = r+1;
//    boolean valid = false;
//    int t = 2;
//    while(c<7) {
//        tempListOfCoordinates[t] = c;
//        t++;
//        tempListOfCoordinates[t] = r;
//        t++;
//        c++;
//        if(othelloBoard.getTile(c, r) == playing){
//            valid = true;
//            setArrayOfCoordinates(tempListOfCoordinates);
//            break;
//        }
//    }
//    return valid;
//}
//
//public boolean checkDR(int c, int r, char playing) {
//    int[] tempListOfCoordinates;
//    tempListOfCoordinates[0] = c+1;
//    tempListOfCoordinates[1] = r+1;
//    boolean valid = false;
//    int t = 2;
//    while(c<7 && r<7) {
//        tempListOfCoordinates[t] = c;
//        t++;
//        tempListOfCoordinates[t] = r;
//        t++;
//        c++;
//        r++;
//        if(othelloBoard.getTile(c, r) == playing){
//            valid = true;
//            setArrayOfCoordinates(tempListOfCoordinates);
//            break;
//        }
//    }
//    return valid;
//}
//
//public void setArrayOfCoordinates(int[] tempListOfCoordinates) {
//    int k = listOfCoordinates.length - 1;
//    int tot = k + tempListOfCoordinates.length - 1;
//    int t = 0;
//    for (int i = k; i < tot; i++) {
//        listOfCoordinates[i] = tempListOfCoordinates[t];
//        t++;
//    }
//}
//
//}
