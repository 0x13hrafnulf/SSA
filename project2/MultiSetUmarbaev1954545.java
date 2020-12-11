
/* JML Exercise: 
   
   This class implements a MultiSet of integers, using an array.

   Add JML specs to this class, and if necessary modify the code, to stop openJML from complaining.


   The only JML keywords needed are
      requires
      invariant 
      ensures 
  
   If you want, you can also use
      non_null
   as abbreviation.


   While developing your specs, it may be useful to use the keyword
      assert
   to add additional assertions in source code, to find out what 
   JML can - or cannot - prove at a given program point.


*/

class MultiSet {
 
  int[] elements;
  int n;
  //@ invariant elements != null;
  //@ invariant n >= 0;
  //@ invariant n <= elements.length;

  MultiSet() 
  {
    elements = new int[0];
    n = 0;
  }

  //@ requires input != null;
  MultiSet(int[] input) 
  {
  
    n = input.length;
    elements = new int[n];
    arraycopy(input, 0, elements, 0, n);
   
  }

  //@ requires b != null;
  MultiSet(MultiSet b) {
    elements = new int[0];
    n = 0;
    this.add(b);    
  }

  //@ requires b != null;
  void add(MultiSet b) 
  {
    int size = Math.addExact(n, b.n);
    if((size >= Integer.MIN_VALUE) && (size <= Integer.MAX_VALUE))
    {
      //@assume (b.n + n >= Integer.MIN_VALUE) && (b.n + n <= Integer.MAX_VALUE);
      int[] new_elements = new int[b.n + n];
      arraycopy(this.elements, 0, new_elements, 0, n);
      arraycopy(b.elements, 0, new_elements, n, b.n);   
      elements = new_elements; 
      n = b.n + n;
    }
  }

  //@ requires a != null;
  void add(int[] a) 
  {
    this.add(new MultiSet(a));
  }

  void add(int elt) 
  {
    if (n == elements.length) 
    {
      int size = Math.multiplyExact(n, 2);
      size = Math.addExact(size, 1);
      if((size >= Integer.MIN_VALUE) && (size <= Integer.MAX_VALUE))
      {
        //@assume (2 * n + 1 >= Integer.MIN_VALUE) && (2 * n + 1 <= Integer.MAX_VALUE);
        int[] new_elements = new int[2 * n + 1]; 
        arraycopy(elements, 0, new_elements, 0, n);
        elements = new_elements;
      } 
    }
    elements[n]=elt;
    n++;
  }

  //@ requires src != null;
  //@ requires dest != null;
  //@ requires srcOff >=0;
  //@ requires destOff >=0;
  //@ requires length >=0;
  //@ requires srcOff + length <= src.length;
  //@ requires destOff + length <= dest.length;
  //@ assignable dest[*];
  private static void arraycopy(int[] src,
                                int   srcOff,
                                int[] dest,
                                int   destOff,
                                int   length) {
    /*@ loop_invariant i>=0 && i<=length; @*/
	for( int i=0 ; i<length; i++) {
       dest[destOff+i] = src[srcOff+i];
    }
  }


  void removeOnce(int elt) 
  {
    //@ loop_invariant i >= 0 && i <= n;
    //@ loop_invariant n >= 0 && n <= elements.length;
    for (int i = 0; i < n; i++) {  
      if (elements[i] == elt ) {
         n--;
         elements[i] = elements[n];
         return;
      }
    }
  }

  /* the next method should remove ALL occurrences of the parameter int elt.
   */

  void removeAll(int elt) 
  {
    //@ loop_invariant i >= 0 && i <= n;
    //@ loop_invariant n >= 0 && n <= elements.length;
    for (int i = 0; i < n; i++) {   
      if (elements[i] == elt ) {
         n--;
         elements[i] = elements[n];
         i--;
      }
    }
  }

  //@ ensures \result >= 0;
  int getCount(int elt) 
  {
    int count = 0;
    //@ loop_invariant i >= 0 && i <= n;
    //@ loop_invariant count >= 0;
    for (int i = 0; i < n; i++)
    {
      if (elements[i] == elt)
      {
        if(count < Integer.MAX_VALUE) count++;
      }
    }
    return count;
  }


}
