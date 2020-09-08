public class Queue {

    protected int[] arr;
    protected int size;
    protected int front;
    protected int back;
    protected int capacity;

    public Queue() {
        reassign(5);
    }

    public Queue(int size) {
        reassign(size);
    }

    protected void reassign(int size) {
        this.arr = new int[size];
        this.size = 0;
        this.front = 0;
        this.back = 0;
        this.capacity = arr.length;
    }

    public int capacity() {
        return this.capacity;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // Steps: have to print data from front to back
    // run a loop from i = 0 to size
    // add that i to front_idx and modulos by arr.length
    // that would be our true idx

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < this.size; i++) {
            int idx = (this.front + i) % this.capacity;
            sb.append(this.arr[idx]);
            if (i != this.size() - 1)
                sb.append(',');
        }

        sb.append("]");
        return sb.toString();
    }

    // Steps: First of all check if the size is equal to size of array if true then
    // it means queue is full
    // 1. add value at back pointer
    // 2. update back pointer by keeping in mind that queue could be empty at the
    // beginning
    // so use (back+1)%arr.length
    // 3. increase size

    public void push(int val) throws Exception {
        if (this.size == this.capacity) {
            throw new Exception("QueueSizeFull");
        }
        push_(val);
    }

    protected void push_(int val) {
        this.arr[this.back] = val;
        this.back = (this.back + 1) % this.capacity;
        this.size++;

    }

    // Steps:First of all check if the size is equal to 0 if true then it means
    // queue is empty
    // 1. preserve the value to be deleted rv = arr[front]
    // 2. make the arr[front]=0
    // 3. update front by using (front+1)%arr.length
    // 4. size--

    public int pop() throws Exception {
        if (this.size == 0) {
            throw new Exception("QueueEmpty");
        }

        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (this.front + 1) % this.capacity;
        this.size--;

        return rv;
    }

    public int front() throws Exception {
        if (this.size == 0) {
            throw new Exception("QueueEmpty");
        }

        return this.arr[this.front];
    }

}