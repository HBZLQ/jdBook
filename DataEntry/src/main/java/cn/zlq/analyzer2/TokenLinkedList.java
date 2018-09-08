package cn.zlq.analyzer2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TokenLinkedList implements Iterable<CnToken> {
   public static class Node{
       public CnToken item;
       public Node next;

       Node(CnToken item){
           this.item = item;
           next = null;
       }

       public boolean hasNext(){
           if (next == null){
               return false;
           }else{
               return true;
           }
       }

       public Node getNext(){
           if (hasNext()){
               return next;
           }
           return null;
       }
   }
   private Node head;
   public TokenLinkedList(){
       head = null;
   }

   public void put(CnToken item){
       Node n = new Node(item);
       n.next = head;
       head = n;
   }

   public Node getHead(){
       return head;
   }

   public Iterator<CnToken> iterator(){
       return new LinkIterator(head);
   }

   private class LinkIterator implements Iterator<CnToken> {
       Node itr;

       public LinkIterator(Node begin) {
           itr = begin;
       }

       public boolean hasNext() {
           return itr != null;
       }

       public CnToken next() {
           if (itr == null) {
               throw new NoSuchElementException();
           }
           Node cur = itr;
           itr = itr.next;
           return cur.item;
       }

       public void remove() {
           throw new UnsupportedOperationException();
       }

       public Iterator<CnToken> iterator() {
           return null;
       }
   }
       public String toString(){
           StringBuilder buf = new StringBuilder();
           Node cur = head;
           while(cur != null){
               buf.append(cur.item.termText);
               buf.append('\t');
               cur = cur.next;
           }
           return buf.toString();
       }
}
