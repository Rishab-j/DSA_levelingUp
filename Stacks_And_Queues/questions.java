import java.util.Stack;
import java.util.Arrays;
import java.util.HashMap;

public class questions {
    public boolean isValid(String str) {

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {
                if (st.size() == 0) { // ye wo case h jbb stack empty h but closing brackets aa jaate h
                                      // mtlb closing brackets are more than opening
                    return false;
                } else if (st.peek() == '(' && ch != ')') {
                    return false;
                } else if (st.peek() == '{' && ch != '}') {
                    return false;
                } else if (st.peek() == '[' && ch != ']') {
                    return false;
                } else
                    st.pop(); // jbb sbb kuch shi h pop krdo
            }
        }

        return st.size() == 0 ? true : false; // loop khtm hone ke baad bhi agr stack mei opening brackets h to false ni
                                              // to true
    }

    public static int[] nextSmallerOnRight(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        // Arrays.fill(ans,arr.length);

        for (int i = 0; i < arr.length; i++) {
            while (st.size() != 0 && arr[st.peek()] > arr[i]) {
                int idx = st.pop();
                ans[idx] = i;
            }
            st.push(i);

        }

        while (st.size() != 0) {
            int idx = st.pop();
            ans[idx] = arr.length;
        }

        return ans;
    }

    public static int[] nextSmallerOnLeft(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);

        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.peek()] > arr[i]) {
                int idx = st.pop();
                ans[idx] = i;
            }
            st.push(i);

        }

        // while(st.size()!=0){
        // int idx=st.pop();
        // ans[idx]=arr.length;
        // }

        return ans;

    }

    public static int[] nextGreaterOnLeft(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);

        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.peek()] < arr[i]) {
                int idx = st.pop();
                ans[idx] = i;
            }
            st.push(i);

        }

        // while(st.size()!=0){
        // int idx=st.pop();
        // ans[idx]=arr.length;
        // }

        return ans;

    }

    // Steps: Idea is make a stack of index and allowing only those index whose data
    // is smaller then the top element of stack
    // 1. make a stack of integer
    // 2. make an array ans
    // 3. start a loop from i = 0 to arr.length on given arr
    // in this loop we check if our stack is empty or if the top elemnt is greater
    // or smaller
    // if stack is empty then push the i in stack
    // or push if the data in i is smaller then the data at idx on top of stack
    // else
    // pop the top element which is actually the "idx" of smaller data and put i in
    // arr[idx]
    // repeat until the arr[i] is big
    // 4. at end pop all the remaining indices and put arr.length at arr[idx]

    public static int[] nextGreaterOnRight(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        // Arrays.fill(ans,arr.length);

        for (int i = 0; i < arr.length; i++) {
            while (st.size() != 0 && arr[st.peek()] < arr[i]) {
                int idx = st.pop();
                ans[idx] = i;
            }
            st.push(i);

        }

        while (st.size() != 0) {
            int idx = st.pop();
            ans[idx] = arr.length;
        }

        return ans;

    }

    public static int[] ngor(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, arr.length);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();

        stack.push(-1);
        for (int i = 0; i < arr.length; i++) {
            while (stack.peek() != -1 && arr[stack.peek()] < arr[i]) {
                ans[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }




    // LRU CACHE

    class LRUCache {

        class Node {
            int key = 0;
            int value = 0;

            Node next = null;
            Node prev = null;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        Node head = null;
        Node tail = null;
        int size = 0;
        int maxSize = 0;

        void addLast(Node node) {
            if (this.size == 0) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
                this.tail = node;
            }
            this.size++;
        }

        void removeNode(Node node) {

            if (this.size == 1) {
                this.head = node;
                this.tail = node;
            } else if (node.prev == null) {
                this.head = node.next;

                this.head.prev = null;
                node.next = null;
            } else if (node.next == null) {
                this.tail = node.prev;

                this.tail.next = null;
                node.prev = null;
            } else {
                Node prev = node.prev;
                Node next = node.next;

                prev.next = next;
                next.prev = prev;

                node.next = null;
                node.prev = null;
            }
            this.size--;
        }

        HashMap<Integer, Node> map = new HashMap<>(); // key, node

        LRUCache(int capacity) {
            this.maxSize = capacity;
        }

        int get(int key) {
            if (!map.containsKey(key))
                return -1;

            Node node = map.get(key);
            int rv = node.value;

            removeNode(node);
            addLast(node);

            return rv;
        }

        void put(int key, int value) {
            if (!map.containsKey(key)) {
                Node node = new Node(key, value);
                map.put(key, node);
                addLast(node);
                if (this.size > this.maxSize) {
                    node = this.head;

                    map.remove(node.key);
                    removeNode(node);
                }
            } else {
                int val = get(key);
                if (val != value)
                    map.get(key).value = value;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 9, 6, 4, 1, -1, 3, 5, 8, 2, 4, 1, -1, 6, 8, 6, 5, 4, 9, 8, 7, 6 };
        int[] ans = ngor(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }

}