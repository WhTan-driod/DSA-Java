public class StringReverse<T>
{
    Node header;
    Node tail;
    
    private class Node
    {
        T element;
        Node next;
        private Node(T element, Node next)
        {
            this.element = element;
            this.next = next;
        }

        private Node(T element)
        {
            this.element = element;
        }
    }

    public void push(T element)
    {
        Node newNode = new Node(element);
        if(header == null)
        {
            newNode.next = header;
            header = newNode;
            tail = newNode;
        }
        else
        {
            newNode.next = header;
            header = newNode;
        }
    }

    public T pop()
    {
        if (header == null) return null;
        T revElement = header.element;
        header = header.next;
        if (header == null) tail = null;
        return revElement;
    }

    public void listElements()
    {
        Node temp = header;
        while(temp!=null){
            System.out.print(temp.element);
            temp=temp.next;
        }
    }

    public static void main(String[] args)
    {
        StringReverse<String> firstStack = new StringReverse<String>();
        StringReverse<String> secondStack = new StringReverse<String>();

        firstStack.push("s");
        firstStack.push("t");
        firstStack.push("a");
        firstStack.push("c");
        firstStack.push("k");
        firstStack.listElements();
        System.out.println();

        secondStack.push(firstStack.pop());
        secondStack.push(firstStack.pop());
        secondStack.push(firstStack.pop());
        secondStack.push(firstStack.pop());
        secondStack.push(firstStack.pop());

        secondStack.listElements();
        System.out.println();

    }
}
