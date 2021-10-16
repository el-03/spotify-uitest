Feature: Checking Album or Single Artwork

  @Positive
  Scenario Outline: Checking album or single a particular artist with a correct artwork
    Given I am on Home Page
    When I tap Search Icon on Navigation Bar
    When I am on Search Page
    When I input "<artistName>" in Search Bar on Search Page
    When I tap "Artists" Filter Button on Search Page
    When I tap "<artistName>" Text in Search Result List on Search Page
    When I am on Artist Page of "<artistName>"
    When I tap See discography Button on Artist Page
    When I am on Artist Releases Page
    When I tap "<albumOrSingleName>" Text in Discography List on Artist Releases Page
    When I am on Album or Single Page of "<albumOrSingleName>"
    Then I can see the artwork same as "<artworkFilePath>"
    Examples:
      | artistName | albumOrSingleName | artworkFilePath                     |
      | King Gnu   | BOY               | albumSingle/king_gnu-boy.jpg        |
      | John Mayer | Sob Rock          | albumSingle/john_mayer-sob_rock.jpg |