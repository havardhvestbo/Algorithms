package no.ntnu.idata2302.lab04.BinaryHeap;

public class BinaryHeap {

  private int[] Heap;
  private int size;
  private int maxsize;

  private static final int FRONT = 1;

  public BinaryHeap(int maxsize) {
    this.maxsize = maxsize;
    this.size = 0;

    Heap = new int[this.maxsize + 1];
    Heap[0] = Integer.MIN_VALUE;
  }

  private int parent(int pos) { return pos / 2; }

  private int leftChild(int pos) { return (2 * pos); }

  private int rightChild(int pos) {
    return (2 * pos) + 1;
  }
  private boolean isLeaf(int pos) {
    if (pos > (size / 2)) {
      return true;
    }
    return false;
  }

  private void swap(int fpos, int spos) {
    int tmp;
    tmp = Heap[fpos];

    Heap[fpos] = Heap[spos];
    Heap[spos] = tmp;
  }

  private void extractMin(int pos) {
    if(!isLeaf(pos)){
      int swapPos= pos;
      if(rightChild(pos)<=size)
        swapPos = Heap[leftChild(pos)]<Heap[rightChild(pos)]?leftChild(pos):rightChild(pos);
      else
        swapPos= Heap[leftChild(pos)];

      if(Heap[pos]>Heap[leftChild(pos)] || Heap[pos]> Heap[rightChild(pos)]){
        swap(pos,swapPos);
        extractMin(swapPos);
      }
    }
  }

  public void insert(int element) {

    if (size >= maxsize) {
      return;
    }

    Heap[++size] = element;
    int current = size;

    while (Heap[current] < Heap[parent(current)]) {
      swap(current, parent(current));
      current = parent(current);
    }
  }

  public void print() {
    for (int i = 1; i <= size / 2; i++) {

      System.out.print(
          " PARENT : " + Heap[i]
              + " LEFT CHILD : " + Heap[2 * i]
              + " RIGHT CHILD :" + Heap[2 * i + 1]);

      System.out.println();
    }
  }

  public int remove() {
    int popped = Heap[FRONT];
    Heap[FRONT] = Heap[size--];
    extractMin(FRONT);

    return popped;
  }

  public static void main(String[] arg) {
    System.out.println("The Min Heap is ");
    BinaryHeap minHeap = new BinaryHeap(15);
    minHeap.insert(5);
    minHeap.insert(3);
    minHeap.insert(17);
    minHeap.insert(10);
    minHeap.insert(84);
    minHeap.insert(19);
    minHeap.insert(6);
    minHeap.insert(22);
    minHeap.insert(9);

    minHeap.print();

    System.out.println("The Min val is "
        + minHeap.remove());
  }
}
