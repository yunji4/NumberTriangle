import java.io.*;
import java.sql.Array;
import java.util.List;
import java.util.ArrayList;



/**
 * This is the provided NumberTriangle class to be used in this coding task.
 *
 * Note: This is like a tree, but some nodes in the structure have two parents.
 *
 * The structure is shown below. Observe that the parents of e are b and c, whereas
 * d and f each only have one parent. Each row is complete and will never be missing
 * a node. So each row has one more NumberTriangle object than the row above it.
 *
 *                  a
 *                b   c
 *              d   e   f
 *            h   i   j   k
 *
 * Also note that this data structure is minimally defined and is only intended to
 * be constructed using the loadTriangle method, which you will implement
 * in this file. We have not included any code to enforce the structure noted above,
 * and you don't have to write any either.
 *
 *
 * See NumberTriangleTest.java for a few basic test cases.
 *
 * Extra: If you decide to solve the Project Euler problems (see main),
 *        feel free to add extra methods to this class. Just make sure that your
 *        code still compiles and runs so that we can run the tests on your code.
 *
 */
public class NumberTriangle {

    private int root;

    private NumberTriangle left;
    private NumberTriangle right;

    public NumberTriangle(int root) {
        this.root = root;
    }

    public void setLeft(NumberTriangle left) {
        this.left = left;
    }


    public void setRight(NumberTriangle right) {
        this.right = right;
    }

    public int getRoot() {
        return root;
    }


    /**
     * [not for credit]
     * Set the root of this NumberTriangle to be the max path sum
     * of this NumberTriangle, as defined in Project Euler problem 18.
     * After this method is called, this NumberTriangle should be a leaf.
     *
     * Hint: think recursively and use the idea of partial tracing from first year :)
     *
     * Note: a NumberTriangle contains at least one value.
     */
    public void maxSumPath() {
        // for fun [not for credit]:
    }


    public boolean isLeaf() {
        return right == null && left == null;
    }


    /**
     * Follow path through this NumberTriangle structure ('l' = left; 'r' = right) and
     * return the root value at the end of the path. An empty string will return
     * the root of the NumberTriangle.
     *
     * You can decide if you want to use a recursive or an iterative approach in your solution.
     *
     * You can assume that:
     *      the length of path is less than the height of this NumberTriangle structure.
     *      each character in the string is either 'l' or 'r'
     *
     * @param path the path to follow through this NumberTriangle
     * @return the root value at the location indicated by path
     *
     */
    public int retrieve(String path) {
        // TODO implement this method
        int root_val = this.root;
        NumberTriangle curr_root = null;
        for  (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'l') {

                if (curr_root == null) {
                    curr_root = this.left;
                }
                else{
                    curr_root = curr_root.left;


                }

                root_val = curr_root.root;
            }
            else if (path.charAt(i) == 'r') {
                if (curr_root == null) {
                    curr_root = this.right;
                }
                else{
                    curr_root = curr_root.right;
                }
                root_val = curr_root.root;


            }

        }
        return root_val;
    }

    /** Read in the NumberTriangle structure from a file.
     *
     * You may assume that it is a valid format with a height of at least 1,
     * so there is at least one line with a number on it to start the file.
     *
     * See resources/input_tree.txt for an example NumberTriangle format.
     *
     * @param fname the file to load the NumberTriangle structure from
     * @return the topmost NumberTriangle object in the NumberTriangle structure read from the specified file
     * @throws IOException may naturally occur if an issue reading the file occurs
     */
    public static NumberTriangle loadTriangle(String fname) throws IOException {
        // open the file and get a BufferedReader object whose methods
        // are more convenient to work with when reading the file contents.
        InputStream inputStream = NumberTriangle.class.getClassLoader().getResourceAsStream(fname);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        // TODO define any variables that you want to use to store things

        // will need to return the top of the NumberTriangle,
        // so might want a variable for that.
        NumberTriangle top = null;

        List<NumberTriangle> prev_row = new ArrayList<>();


        String line = br.readLine();
        while (line != null) {
            List<NumberTriangle> curr_row = new ArrayList<>();
            String[] curr_string = line.split(" ");

            if (top ==null){
                top = new NumberTriangle(Integer.parseInt(curr_string[0]));
                prev_row.add(top);
            }
            else{
                for (int i =0; i< curr_string.length; i++){
                    curr_row.add(new  NumberTriangle(Integer.parseInt(curr_string[i])));
                }
                for (int i =0; i< prev_row.size(); i++){
                    prev_row.get(i).setLeft(curr_row.get(i));
                    prev_row.get(i).setRight(curr_row.get(i+1));
                }
                prev_row = curr_row;


            }
            //read the next line
            line = br.readLine();
        }
        br.close();
        return top;
    }

    public static void main(String[] args) throws IOException {

        NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");

        // [not for credit]
        // you can implement NumberTriangle's maxPathSum method if you want to try to solve
        // Problem 18 from project Euler [not for credit]
        mt.maxSumPath();
        System.out.println(mt.getRoot());
    }
}
