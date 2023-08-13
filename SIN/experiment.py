#!/usr/bin/python
import sys
from math import sqrt
import numpy as np
from perceptron import perceptron
from confus import confus
from linmach import linmach

if len(sys.argv)!=4:
  print('Usage: %s <data> <alphas> <bs>' % sys.argv[0]);
  sys.exit(1);

data=np.loadtxt(sys.argv[1]);
N,L=data.shape; D=L-1; labs=np.unique(data[:,L-1]); C=labs.size;
np.random.seed(23); perm=np.random.permutation(N); data=data[perm];
alphas=np.fromstring(sys.argv[2],sep=' ');
bs=np.fromstring(sys.argv[3],sep=' ');

NTr=int(round(.7*N)); train=data[:NTr,:]; M=N-NTr; test=data[NTr:,:]; 

print('#      a       b   E   k Ete Ete (%)    Ite(%)');
print('#------- ------- --- --- --- ------- ---------');

for a in alphas:
  for b in bs:
    w,E,k=perceptron(train,b,a); rl=np.zeros((M, 1));
    for n in range(M):
      rl[n]=labs[linmach(w,np.concatenate(([1],test[n,:D])))]
    nerr,m=confus(test[:,L-1].reshape(M,1),rl)
    perr=nerr/M
    r=1.96*sqrt(perr*(1-perr)/M)
    print('%8.1f %7.1f %3d %3d %3d %7.1f [%.1f,%.1f]' % (a, b, E, k, nerr, perr*100, (perr-r)*100,(perr+r)*100));