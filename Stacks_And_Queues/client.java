public class client {
    public static void main(String[] args) throws Exception {
        //int[] arr = { 10, 20, 30, 40 };
        // DStack st = new DStack(arr);
        // st.push(100);
        // st.push(100);
        // st.push(110);
        // st.push(130);
        // st.top();

        // System.out.println(st);

        Queue queue = new Queue();
        queue.push(10);
        queue.push(20);
        queue.push(30);
        queue.push(40);
        queue.push(50);
        queue.pop();
        
        System.out.println(queue);
        System.out.println(queue.front());

    }
}