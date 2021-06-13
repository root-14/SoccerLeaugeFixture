# Soccer League Fixture

This is a match fixture app. Getting team names data from REST API. Determining fixture for two half of league matches.

API that i'm used : 'mockapi.io'. This site basically providing us JSON data that before we define.

# Algorithm

There is basically two algorithm we have. One is making shuffles the given array.  Other one is matches shuffled array. Here is the working logic of algorithm.

## Shuffle Logic
The method generally used in the world called like Cycling Method.



> You can view working logic of method:
> https://www.dpsbokaro.com/dpsi/docs/econtent-2020-21/senior/08.04.2020/XII/PE.pdf

Basically, it moves the array clockwise. Here is the example:
<p align="center">
  <img src="https://github.com/root-14/SoccerLeaugeFixture/blob/master/screentshots/algorithm.png" width="200" title="img0">
  </p>

## Matching algorithm

Returned array arranged to match. Basically its matching one from start, one from end. Here is the example: 
{1,2,3,4,5,6} -> {1-6 , 2-5 , 3-4}

## Extra points
 - Supporting dark mode from system settings
 - Animation when passing from team list screen to first-week match fixture.
 - Application and fixture algorithm support different team counts in the league.
 
 ###  Screenshots
 
 <p align="center">
  <img src="https://github.com/root-14/SoccerLeaugeFixture/blob/master/screentshots/ss0.png" width="150" title="img0">
  <img src="https://github.com/root-14/SoccerLeaugeFixture/blob/master/screentshots/ss1.png" width="150" title="img0">
  <img src="https://github.com/root-14/SoccerLeaugeFixture/blob/master/screentshots/ss2.png" width="150" title="img0">
  <img src="https://github.com/root-14/SoccerLeaugeFixture/blob/master/screentshots/ss3.png" width="150" title="img0">
  <img src="https://github.com/root-14/SoccerLeaugeFixture/blob/master/screentshots/aim.gif" width="150" title="img0">
  </p>
