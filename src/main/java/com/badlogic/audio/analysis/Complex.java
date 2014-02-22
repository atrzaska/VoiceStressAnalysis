package com.badlogic.audio.analysis;

import java.lang.Math;

public class Complex {
  
  protected double re;
  protected double im;
  
  public Complex(double real, double imag) {
    re = real;
    im = imag;
  }
  
  public double getReal(){
    return re;
  }
  
  public double getImaginary(){
    return im;
  }

  @Override
  public String toString(){
    return re + ((Math.signum(im) >= 0)?" + ":" - ") + Math.abs(im)+"i";
  }
  
  @Override
  public boolean equals(Object obj){
    if(this == obj)
      return true;
    if(obj == null)
      return false;
    if(getClass() != obj.getClass())
      return false;
    Complex that = (Complex)obj;
    return (that.re == this.re) && (that.im == this.im);
  }
  
  public Complex add(Complex b){
    re = re+b.re;
    im = im+b.im;
    return this;
  }
  
	public float abs() {
		return (float) Math.hypot(re, im);
	}
  
  public static Complex add(Complex a, Complex b){
    return new Complex(a.re + b.re, a.im + b.im);
  }
}
