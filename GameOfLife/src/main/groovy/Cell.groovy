import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor

@TupleConstructor(includeFields = true)
@EqualsAndHashCode(includeFields = true)
@Canonical
class Cell {
  private int x;
  private int y;

  def neighbours() {
    def cellNeighbours = [];
    cellNeighbours.add(new Cell(x-1, y-1));
    cellNeighbours.add(new Cell(x-1, y));
    cellNeighbours.add(new Cell(x, y-1));
    cellNeighbours.add(new Cell(x+1, y+1));
    cellNeighbours.add(new Cell(x+1, 0));
    cellNeighbours.add(new Cell(x, y+1));
    cellNeighbours.add(new Cell(x-1, y+1));
    cellNeighbours.add(new Cell(x+1, y-1));
    return cellNeighbours;
  }

}

