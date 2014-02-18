import spock.lang.*

//Any live cell with fewer than two live neighbours dies, as if caused by under-population.
//Any live cell with two or three live neighbours lives on to the next generation.
//Any live cell with more than three live neighbours dies, as if by overcrowding.
//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

class TestGameOfLife extends Specification {
  World world;

  def setup() {
    world = new World();
  }
  def "testWorldGivesCellLifeIsAlive"() {
    Cell cell = new Cell(0,0);
    world.giveCellLife(cell);
    expect:
      world.isCellAlive(cell) == true;
  }

  def "testOnlyCellsThatHaveBeenGiveLifeShouldBeAlive"(){
    Cell cell = new Cell(9,0);
    Cell anotherCell = new Cell(4,3);
    world.giveCellLife(cell);
    expect:
      world.isCellAlive(cell) == true;
      world.isCellAlive(anotherCell) == false;
  }

  def "testLiveCellWithOneLiveNeighbourDiesOnNextTick"() {
    Cell cell = new Cell(0,0);
    Cell neighborCell = new Cell(1,0);
    world.giveCellLife(cell);
    world.giveCellLife(neighborCell);
    expect:
    world.tick().isCellAlive(cell) == false;
  }

  void "testLiveCellWithZeroLiveNeighboursDiesOnNextTick"() {
    Cell cell = new Cell(3,4);
    world.giveCellLife(cell);
    expect:
      world.tick().isCellAlive(cell) == false;
  }

  void "testCellHasEightNeighbours"() {
    Cell cell = new Cell(9,2);
    expect:
      cell.listOfNeighbours().size() == 8;
  }
  @Ignore
  void "testCellShouldKnowItsNeighbours"() {
    Cell cell = new Cell(0,0);
    def listOfNeighbours = [];
    listOfNeighbours = cell.neighbours();
    expect:
      listOfNeighbours.each {
        false;
      }
  }

  @Ignore
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
}
