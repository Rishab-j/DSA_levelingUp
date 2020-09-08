public class DQueue extends Queue {
    // whenever we extend a class then all the members and functions except the
    // priavte ones and constructors are copied here at the Runtime

    // at least utne constructor to likhne hi h jitne parent mei h

    DQueue() {
        super(); // ye ab parent mei rkhe constructor ko call krdega
        // agr ye likha hota super.pop() iska mtlb ki parent class ke pop() function ko
        // call kro
    }

    DQueue(int size) {
        super(size);// ye ab parent mei rkhe constructor ko call krdega
    }

    // This is function makes our queue dynamic so now it doesn't matter if our
    // array's length is low
    // Steps: Override our push function
    // 1. Copy old data in a temporary array so that our data is not lost
    // 2. Preserve the front pointer because we would want to add data in our new
    // array with double the size with first element = temp[fr]
    // 3. reassign new size to our original array, now the size is double and both
    // front and back are pointing at 0
    // 4. run a loop from i = 0 to temp.length ,to push our data again to the
    // original array
    // 5. after pushing all the data Push the data you wanted to push.

    @Override // ye likhna compulsory ni hota h but we will write it
    // beacuse its a good practice

    public void push(int val) throws Exception { // by making dynamic queue we do not have the problem of space
        if (super.size() == super.capacity()) {
            int[] temp = super.arr; // copy old data.
            // same to same jaisa tha waisa

            int fr = super.front;

            super.reassign(2 * temp.length); // ye apne originl array ka hi size double krta h

            // we will enter data in our new array in the order of which the data was
            // entered in our queue

            for (int i = 0; i < temp.length; i++) { // temp mei jitna bhi data h sbb original arr mei push kro
                int idx = (fr + i) % temp.length;
                super.push_(temp[idx]); // original arr mei hi push krta h
                // super.push(val) is also correct

            }
        }

        super.push_(val);
    }
}
