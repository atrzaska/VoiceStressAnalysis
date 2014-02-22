package transforms;

import com.badlogic.audio.analysis.Complex;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

/**
 * Wrapper class over JTransforms library for fourier transforms.
 * @author apurv
 *
 */
public class FFT {

	public static Complex[] fft1D(Complex[] signal){
		int n = signal.length;
		Complex[] fourier = new Complex[n];
		double[] coeff = new double[2*n];
		int i = 0;
		for(Complex c:signal){
			coeff[i++] = c.getReal();
			coeff[i++] = c.getImaginary();
		}
		DoubleFFT_1D fft = new DoubleFFT_1D(n);
		fft.complexForward(coeff);
		for(i = 0; i < 2*n; i+=2){
			Complex c = new Complex(coeff[i], coeff[i+1]);
			fourier[i/2] = c;
		}
		return fourier;
	}
	
	public static Complex[] fftShift1D(Complex[] fTransform){
		int n = fTransform.length;
		int mid = (n-1)/2;
		Complex[] shift = new Complex[n];
		int j = 0;
		for(int i = mid+1; i < n; i++){
			shift[j] = fTransform[i];
			j++;
		}
		for(int i = 0; i <= mid; i++){
			shift[j] = fTransform[i];
			j++;
		}
		return shift;
	}
	
	public static Complex[] ifft1D(Complex[] fourier){
		int n = fourier.length;
		double s = 1.0 / (double) n;
		Complex[] signal = new Complex[n];
		double[] coeff = new double[2*n];
		int i = 0;
		for(Complex c:fourier){
			coeff[i++] = c.getReal();
			coeff[i++] = c.getImaginary();
		}
		DoubleFFT_1D fft = new DoubleFFT_1D(n);
		fft.complexInverse(coeff, false);
		for(i = 0; i < 2*n; i+=2){
			Complex c = new Complex(s*coeff[i], s*coeff[i+1]);
			signal[i/2] = c;
		}
		return signal;
	}
	
	public static Complex[] ifftShift1D(Complex[] fourier){
		int n = fourier.length;
		int mid = n/2;
		Complex[] shift = new Complex[n];
		int j = 0;
		for(int i = mid; i < n; i++){
			shift[j] = fourier[i];
			j++;
		}
		for(int i = 0; i < mid; i++){
			shift[j] = fourier[i];
			j++;
		}
		return shift;
	}
}