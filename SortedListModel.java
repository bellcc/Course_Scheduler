import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

class SortedListModel extends AbstractListModel<Object> {

   SortedSet<Object> model;

   public SortedListModel() {
      model = new TreeSet<Object>();
   }

   public int getSize() {
      return model.size();
   }

   public Object getElementAt(int index) {
      return model.toArray()[index];
   }

   public void add(Object element) {
      if(model.add(element)) {
         fireContentsChanged(this, 0, getSize());
      }
   }

   public void addAll(Object elements[]) {
      Collection<Object> c = Arrays.asList(elements);
      model.addAll(c);
      fireContentsChanged(this, 0, getSize());
   }

   public void clear() {
      model.clear();
      fireContentsChanged(this, 0, getSize());
   }

   public boolean contains(Object element) {
      return model.contains(element);
   }
  
   public Iterator<Object> iterator() {
	  return model.iterator();
   }

   public boolean removeElement(Object element) {
      boolean removed = model.remove(element);
      if(removed) {
         fireContentsChanged(this, 0, getSize());
      }
      return removed;
   }
}