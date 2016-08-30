JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	Quote.java \
	Stock.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm *.class
