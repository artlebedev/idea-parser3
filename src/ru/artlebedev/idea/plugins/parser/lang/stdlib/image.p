@CLASS
image

@measure[file]
###

@measure[filename]
###

@create[x;y]
###

@create[x;y;background]
###

@load[filename]
###

#:dynamic
@html[]
$result[]
###

#:dynamic
@html[options]
$result[]
###

#:dynamic
@gif[]
$result[^file::create[binary;filename;text]]
###

#:dynamic
@line[x0;y0;x1;y1;color]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@pixel[x;y]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@pixel[x;y;color]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@fill[x;y;color]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@rectangle[x0;y0;x1;y1;color]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@bar[x0;y0;x1;y1;color]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@polyline[color;table]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@polygon[color;table]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@polybar[color;table]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@replace[oldcolor;newcolor;table]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@replace[oldcolor;newcolor]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@replace[x;y;radius;color]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@arc[x;y;width;height;start;end;color]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@sector[x;y;width;height;start;end;color]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@font[letters;filename;spacewidth]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@font[letters;filename;spacewidth;charwidth]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@font[letters;filename;options]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@text[x;y;text]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@length[text]
$result(1)
###

#:dynamic
@copy[source;x1;y1;width1;height1;x2;y2]
$result[^void::_has_no_constructor[]]
###

#:dynamic
@copy[source;x1;y1;width1;height1;x2;y2;width2;height2;hsr]
$result[^void::_has_no_constructor[]]
###