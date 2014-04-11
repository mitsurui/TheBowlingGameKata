import spock.lang.*

class BowlingGameSpec extends Specification{
  def "testGutterGamee"() {
    Game game = new Game()
    for(int i=0; i<20; i++) 
      game.roll(0)
    expect:
    game.score() == 0
    
  }  
}
