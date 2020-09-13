#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct ListNode
{
    int val;
    ListNode *next;

    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

//Leetcode 876
ListNode *middleNode(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

ListNode *middleNode2(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *slow = head;
    ListNode *fast = head;

    while (fast->next != nullptr && fast->next->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

//Leetcode 206
ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *prev = nullptr;
    ListNode *forw = nullptr;
    ListNode *curr = head;

    while (curr != nullptr)
    {
        forw = curr->next; // backup

        curr->next = prev; // link

        prev = curr; // move
        curr = forw;
    }

    return prev;
}

//Leetcode 206
ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *prev = nullptr;
    ListNode *forw = nullptr;
    ListNode *curr = head;

    while (curr != nullptr)
    {
        forw = curr->next; // backup

        curr->next = prev; // link

        prev = curr; // move
        curr = forw;
    }

    head=prev;
    return head;
}

void reveseData(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *mid = middleNode2(head);
    ListNode *tHead = mid->next;
    mid->next = nullptr;

    tHead = reverseList(tHead);
    ListNode *c1 = head;
    ListNode *c2 = tHead;

    while (c1 != nullptr && c2 != nullptr)
    {
        int temp = c1->val;
        c1->val = c2->val;
        c2->val = temp;

        c1 = c1->next;
        c2 = c2->next;
    }

    tHead = reverseList(tHead);
    mid->next = tHead;
}

//Leetcode 234

bool isPalindrome(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return true;

    ListNode *mid = middleNode2(head);
    ListNode *tHead = mid->next;
    mid->next = nullptr;

    tHead = reverseList(tHead);
    ListNode *c1 = head;
    ListNode *c2 = tHead;

    bool res = true;
    while (c1 != nullptr && c2 != nullptr)
    {
        if (c1->val != c2->val)
        {
            res = false;
            break;
        }

        c1 = c1->next;
        c2 = c2->next;
    }

    tHead = reverseList(tHead);
    mid->next = tHead;

    return res;
}

//Leetcode 143
void reorderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *mid = middleNode2(head);
    ListNode *tHead = mid->next;
    mid->next = nullptr;

    tHead = reverseList(tHead);
    ListNode *c1 = head;
    ListNode *c2 = tHead;

    ListNode *f1 = nullptr;
    ListNode *f2 = nullptr;

    while (c1 != nullptr && c2 != nullptr)
    {
        f1 = c1->next; // backup
        f2 = c2->next;

        c1->next = c2; // link
        c2->next = f1;

        c1 = f1; // move
        c2 = f2;
    }
}

ListNode *th1 = nullptr;
ListNode *tt1 = nullptr;
ListNode *th2 = nullptr;
ListNode *tt2 = nullptr;

void addFirst(ListNode *node)
{
    if (th1 == nullptr)
    {
        th1 = node;
        tt1 = node;
    }
    else
    {
        node->next = th1;
        th1 = node;
    }
}

void addLast(ListNode *node)
{
    if (th2 == nullptr)
    {
        th2 = node;
        tt2 = node;
    }
    else
    {
        tt2->next = node;
        tt2 = node;
    }
}

ListNode *orderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    int cnt = 0;
    ListNode *curr = head;
    while (curr != nullptr)
    {
        ListNode *forw = curr->next;
        curr->next = nullptr;

        if (cnt == 0)
            addLast(curr);
        else
            addFirst(curr);

        curr = forw;
        cnt = (cnt + 1) % 2;
    }

    tt2->next = th1;
    return th2;
}

//Leetcode
ListNode *mergeTwoLists(ListNode *l1, ListNode *l2)
{
    if (l1 == nullptr || l2 == nullptr)
        return (l1 == nullptr ? l2 : l1);

    ListNode *dummy = new ListNode(-1);
    ListNode *prev = dummy;

    ListNode *c1 = l1;
    ListNode *c2 = l2;
    while (c1 != nullptr && c2 != nullptr)
    {

        if (c1->val < c2->val)
        {
            prev->next = c1;
            c1 = c1->next;
        }
        else
        {
            prev->next = c2;
            c2 = c2->next;
        }

        prev = prev->next;
    }

    if (c1 != nullptr)
        prev->next = c1;
    else
        prev->next = c2;

    return dummy->next;
}

//Leetcode 148
ListNode *sortList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *mid = middleNode2(head);
    ListNode *nhead = mid->next;
    mid->next = nullptr;

    return mergeTwoLists(sortList(head), sortList(nhead));
}

//Leetcode 23
ListNode *mergeKLists(vector<ListNode *> &lists, int si, int ei)
{
    if (si == ei)
        return lists[si];

    int mid = (si + ei) / 2;
    return mergeTwoLists(mergeKLists(lists, si, mid), mergeKLists(lists, mid + 1, ei));
}

ListNode *mergeKLists(vector<ListNode *> &lists)
{
    if (lists.size() == 0)
        return nullptr;
    return mergeKLists(lists, 0, lists.size() - 1);
}

class compare
{
public:
    bool operator()(const ListNode *a, const ListNode *b) const
    {
        return a->val > b->val; // this- other , default.
    }
};

ListNode *mergeKLists(vector<ListNode *> &lists)
{
    if (lists.size() == 0)
        return nullptr;

    // PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->{
    //     return a.val - b.val;
    // });

    priority_queue<ListNode *, vector<ListNode *>, compare> que;
    for (int i = 0; i < lists.size(); i++)
        que.push(lists[i]);

    ListNode *dummy = new ListNode(-1);
    ListNode *prev = dummy;

    while (que.size() != 1)
    {
        ListNode *node = que.top();
        que.pop();

        ListNode *next = node->next; // backup
        node->next = nullptr;

        prev->next = node; // link
        prev = node;

        if (next != nullptr)
            que.push(next); // move
    }

    prev->next = que.top();
    que.pop();

    return dummy->next;
}

//Leetcode 25
int len(ListNode *head)
{
    int l = 0;
    while (head != nullptr)
    {
        l++;
        head = head->next;
    }
    return l;
}

ListNode *reverseKGroup(ListNode *head, int k)
{
    if (head == nullptr || head->next == nullptr || k <= 1)
        return head;

    int l = len(head);
    if (l < k)
        return head;

    ListNode *curr = head;

    ListNode *oh = nullptr;
    ListNode *ot = nullptr;

    int K = k;
    while (curr != nullptr && l >= k)
    {
        while (K-- > 0)
        {
            ListNode *next = curr->next;
            curr->next = nullptr;

            addFirst(curr);

            curr = next;
        }

        if (oh == nullptr)
        {
            oh = th1;
            ot = tt1;
        }
        else
        {
            ot->next = th1;
            ot = tt1;
        }

        th1 = nullptr;
        tt1 = nullptr;

        l -= k;
        K = k;
    }

    ot->next = curr;
    return oh;
}

//Leetcode 92
ListNode *reverseBetween(ListNode *head, int m, int n)
{
    if (head == nullptr || head->next == nullptr || m == n)
        return head;

    ListNode *curr = head;
    ListNode *prev = nullptr;

    int idx = 1;
    while (curr != nullptr)
    {
        while (idx >= m && idx <= n)
        {
            ListNode *next = curr->next;
            curr->next = nullptr;

            addFirst(curr);
            curr = next;
            idx++;
        }

        if (idx > n)
        {
            if (prev == nullptr)
                head = th1;
            else
                prev->next = th1;

            tt1->next = curr;
            break;
        }

        prev = curr;
        curr = curr->next;
        idx++;
    }

    return head;
}



