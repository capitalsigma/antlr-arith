BASE=/home/patrick/hacking/compilers/antlr/antlr-arith
OUT=$BASE/out
INPUT=$BASE/grammar/Arithmetic.gr4
JAVA=$BASE/java
ANTLR=/usr/local/lib/antlr-4.2.2-complete.jar

$ANTLR  -o $OUT -no-listener -visitor  $INPUT
cp $JAVA/* $OUT/
javac $OUT/*.java
