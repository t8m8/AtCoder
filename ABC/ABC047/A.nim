import strutils, sequtils, algorithm, future

{.warning[SmallLshouldNotBeUsed]: off.}

when isMainModule:
  var
    input = stdin.readLine.split.map(parseInt).sorted(cmp)
  if input[0] + input[1] == input[2]:
    echo "Yes"
  else:
    echo "No"