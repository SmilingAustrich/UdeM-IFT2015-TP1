# UdeM-IFT2015-TP1: Solveur Sudoku

## Description

This project was created as part of the **IFT2015 - Data Structures** course during the Fall 2024 semester at the **University of Montreal**. It implements an object-oriented Sudoku solver capable of resolving 9x9 Sudoku puzzles according to standard rules.

### Objective

Develop a program that solves Sudoku puzzles. The program takes a 9x9 grid with some cells pre-filled with digits between 1 and 9 and fills in the remaining cells to complete the puzzle. The solution must ensure:

1. Each digit (1-9) appears **exactly once in each row**.
2. Each digit (1-9) appears **exactly once in each column**.
3. Each digit (1-9) appears **exactly once in each 3x3 sub-grid**.

## Features

- Accepts one or more Sudoku puzzles in the form of 9x9 grids.
- Detects invalid input sizes (e.g., grids not sized 9x9) and reports an error.
- Outputs:
  - The solved puzzle if a solution exists.
  - An error message, `"No solution exists"`, if the puzzle is unsolvable.
  - An error message, `"Illegal dimensions"`, if the grid dimensions are incorrect.

## Rules of Sudoku

- Every digit from **1 to 9** must appear **exactly once per row**.
- Every digit from **1 to 9** must appear **exactly once per column**.
- Every digit from **1 to 9** must appear **exactly once in each 3x3 sub-grid**.

## Input/Output Specifications

### Input

The program processes 9x9 grids represented as 2D arrays of integers (Java `int[][]`):

- **Pre-filled cells:** Numbers from `1` to `9`.
- **Empty cells:** Represented by `0`.

### Output

- If the puzzle is solvable:
  - Outputs `"Solved!"` followed by the completed grid (9 rows of 9 digits separated by spaces).
- If the puzzle is unsolvable:
  - Outputs `"No solution exists"`.
- If the grid dimensions are invalid:
  - Outputs `"Illegal dimensions"`.

## Testing

The project includes a test suite, **`SudokuApp`**, which validates the program against multiple puzzles. The application must pass all these tests to achieve full marks.

## Usage

1. Clone the repository:
   ```bash
   git clone https://github.com/SmilingAustrich/UdeM-IFT2015-TP1.git
   ```
2. Navigate to the project directory:
   ```bash
   cd UdeM-IFT2015-TP1
   ```
3. Compile the project:
   ```bash
   javac SudokuApp.java
   ```
4. Run the application:
   ```bash
   java SudokuApp
   ```

## Implementation Details

- **Language:** Java
- **Class:** The main logic resides in the `SudokuApp` class.
- **Algorithm:** The solution employs backtracking to systematically explore possible solutions for empty cells while adhering to Sudoku rules.

## Example

### Input
A 9x9 grid with some cells pre-filled:
```
5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9
```

### Output
```
Solved!
5 3 4 6 7 8 9 1 2
6 7 2 1 9 5 3 4 8
1 9 8 3 4 2 5 6 7
8 5 9 7 6 1 4 2 3
4 2 6 8 5 3 7 9 1
7 1 3 9 2 4 8 5 6
9 6 1 5 3 7 2 8 4
2 8 7 4 1 9 6 3 5
3 4 5 2 8 6 1 7 9
```

## Contributors

- **Tarek & Ilyesse **  
For questions, feel free to open an issue or contact the repository owner or collaborator.

## License

This project is for educational purposes only and is subject to the policies of the University of Montreal. Redistribution or commercial use is prohibited.
