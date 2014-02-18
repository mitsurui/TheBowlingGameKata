import spock.lang.*

//Any live cell with fewer than two live neighbours dies, as if caused by under-population.
//Any live cell with two or three live neighbours lives on to the next generation.
//Any live cell with more than three live neighbours dies, as if by overcrowding.
//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

class TestGameOfLife extends Specification {
  World world;
  Cell cell;

  def setup() {
    world = new World();
    cell = new Cell(5,9);
  }
  def "testWorldGivesCellLifeIsAlive"() {
    world.giveCellLife(cell);
    expect:
      world.isCellAlive(cell) == true;
  }

  def "testOnlyCellsThatHaveBeenGiveLifeShouldBeAlive"(){
    Cell anotherCell = new Cell(4,3);
    world.giveCellLife(cell);
    expect:
      world.isCellAlive(cell) == true;
      world.isCellAlive(anotherCell) == false;
  }

  def "testLiveCellWithOneLiveNeighbourDiesOnNextTick"() {
    world.giveCellLife(cell);
    world.giveCellLife(cell.neighbours()[2]);
    expect:
    world.tick().isCellAlive(cell) == false;
  }

  void "testLiveCellWithZeroLiveNeighboursDiesOnNextTick"() {
    world.giveCellLife(cell);
    expect:
      world.tick().isCellAlive(cell) == false;
  }

  void "testCellHasEightNeighbours"() {
    expect:
      cell.neighbours().size() == 8;
  }

  void "testCellShouldKnowItsNeighbours"() {
    Cell cell = new Cell(0,0);
    def listOfNeighbours = cell.neighbours();
    expect:
      listOfNeighbours[0] == new Cell(-1,-1);
      listOfNeighbours[1] == new Cell(-1, 0);
      listOfNeighbours[2] == new Cell(0, -1);
      listOfNeighbours[3] == new Cell(1, 1);
      listOfNeighbours[4] == new Cell(1, 0);
      listOfNeighbours[5] == new Cell(0, 1);
      listOfNeighbours[6] == new Cell(-1, 1);
      listOfNeighbours[7] == new Cell(1, -1);
  }

  void "testLiveCellWithTwoNeighboursLivesOnNexTick"() {
    Cell cell = new Cell(1,1);
    Cell neighbour = new Cell(0,1);
    Cell anotherNeighbour = new Cell(2,2);
    world.giveCellLife(cell);
    world.giveCellLife(neighbour);
    world.giveCellLife(anotherNeighbour);
    expect:
      world.tick().isCellAlive(cell) == true;
  }

  void "testLiveCellWithThreeLiveNeighboursLivesOnNextTick"() {
    Cell cell = new Cell(0,0);
    world.giveCellLife(cell);
    world.giveCellLife(cell.neighbours()[1]);
    world.giveCellLife(cell.neighbours()[2]);
    world.giveCellLife(cell.neighbours()[3]);
    expect:
      world.tick().isCellAlive(cell) == true;
  }

  void "testLiveCellWithMoreThanThreeLiveNeighboursDiesOnNextTick"() {
    world.giveCellLife(cell);
    world.giveCellLife(cell.neighbours()[1]);
    world.giveCellLife(cell.neighbours()[2]);
    world.giveCellLife(cell.neighbours()[3]);
    world.giveCellLife(cell.neighbours()[4]);
    expect:
      world.tick().isCellAlive(cell) == false;
  }

  void "testDeadCellWithExactlyThreeLiveNeighboursLivesOnNextTick"() {
    world.giveCellLife(cell.neighbours()[7]);
    world.giveCellLife(cell.neighbours()[6]);
    world.giveCellLife(cell.neighbours()[5]);
    expect:
      world.tick().isCellAlive(cell) == true;
  }
}
