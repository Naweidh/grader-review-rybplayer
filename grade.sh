CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'
CPATHNEW='.:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

echo ""
# Clone student repository
git clone $1 student-submission
echo 'Finished cloning'

echo ""
# Find relative path to file
FILEPATH=`find student-submission/ -name ListExamples.java`

# Check if file exists in direct directory
if [ ! -f "./student-submission/ListExamples.java" ]; then
    echo "ListExamples.java not in current directory."

    # Check if file exists in some subdirectory
    if [ -z $FILEPATH ]; then
        echo "ListExamples is not in any subdirectories either."
        exit
    else 
        echo "ListExamples is in a subdirectory."
    fi
else
    echo "ListExamples in current directory."
fi

echo ""
# Copy file to grading-area
cp $FILEPATH grading-area
cp TestListExamples.java grading-area
echo 'Copy Complete'

echo ""
# Compile the file and tests
javac -cp $CPATH grading-area/*.java
cd grading-area
java -cp $CPATHNEW org.junit.runner.JUnitCore TestListExamples > testResults.txt
cd ..
echo 'Compile and ran'

# Get a grade
numtest=`grep "@Test" grading-area/TestListExamples.java | wc -l`

if grep "OK" grading-area/testResults.txt; then
    echo "FULL MARKS"
    echo "Score: " $numtest / $numtest
    exit
fi

result=`grep "Tests run: " grading-area/testResults.txt`

score=$(($numtest - ${result:(-2)}))
echo ""
echo "Score: " $score / $numtest
