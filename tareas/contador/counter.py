import numpy
import scipy.special
import math
import random

class red:
    def __init__(self, in, hidN, out, lrate):
        self.inodes = in
        self.hnodes = hidN
        self.onodes = out
        self.wih = numpy.random.normal(0.001, pow(self.inodes, -0.5), (self.hnodes, self.inodes))
        self.w = numpy.random.normal(0.001, pow(self.hnodes, -0.5), (self.onodes, self.hnodes))
        self.lr = lrate
        self.activation_function = lambda x: scipy.special.expit(x)
        pass

    def sethidN(self, num):
        self.hnodes = num
    def train(self, inlist, tlist):
        inp = numpy.array(inlist, ndmin=2).T
        targets = numpy.array(tlist, ndmin=2).T
        
        hidden_inp = numpy.dot(self.wih, inp)
        hout = self.activation_function(hidden_inp)
        
        finp = numpy.dot(self.w, hout)
        fout = self.activation_function(finp)
        erro = targets - fout
        herro = numpy.dot(self.w.T, erro) 
        self.w += self.lr * numpy.dot((erro * fout * (0.999 - fout)), numpy.transpose(hout))
        self.wih += self.lr * numpy.dot((herro * hout * (0.999 - hout)), numpy.transpose(inp))
        pass

    
    def query(self, inlist):
        inp = numpy.array(inlist, ndmin=2).T
        hidden_inp = numpy.dot(self.wih, inp)
        hout = self.activation_function(hidden_inp)
        finp = numpy.dot(self.w, hout)
        fout = self.activation_function(finp)
        return fout

inN = 3
outN = 3
a = 7
cant = 400
hNodes = int((cant*8)/(a*(inN+outN)))
lrarte = 1

nt = red(inN,hNodes,outN, lrarte)

def train(it):

    for i in range(it):
        nt.train([ 0.0 ,  0.0 ,  0.0 ], [ 0.0 ,  0.0 ,  1.0 ])
        nt.train([ 0.0 ,  0.0 ,  1.0 ], [ 0.0 ,  1.0 ,  0.0 ])
        nt.train([ 0.0 ,  1.0 ,  0.0 ], [ 0.0 ,  1.0 ,  1.0 ])
        nt.train([ 0.0 ,  1.0 ,  1.0 ], [ 1.0 ,  0.0 ,  0.0 ])

train(cant)
