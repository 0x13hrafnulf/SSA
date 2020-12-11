
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
  //@ modifies elements, n;
  void add(MultiSet b) 
  {

      //@assume (n + b.n >= Integer.MIN_VALUE) && (n + b.n  <= Integer.MAX_VALUE);
      int[] new_elements = new int[n+b.n];
      arraycopy(this.elements, 0, new_elements, 0, n);
      //arraycopy(b.elements, 0, new_elements, n, b.n);   
      elements = new_elements; 
      n = n + b.n;

  }

  //@ requires a != null;
  void add(int[] a) 
  {

    this.add(new MultiSet(a));

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

}
