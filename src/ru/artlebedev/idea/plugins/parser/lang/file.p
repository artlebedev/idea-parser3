@CLASS
file

@load[format;filename]
$name[]
$size[]
$text[]
$cdate[^date::create[]]
$mdate[^date::create[]]
$adate[^date::create[]]
$stderr[]
$status[]
$mode[]
$content-type[]
###

@load[format;filename;download_options]
###

@load[format;filename;newname]
###

@load[format;filename;newname;download_options]
###

@sql[query]
###

@sql[query;options]
###

@stat[filename]
###

@cgi[filename]
###

@cgi[filename;env_hash]
###

@cgi[filename;env_hash;*args]
###

@cgi[format;filename;env_hash;*args]
###

@exec[filename]
###

@exec[filename;env_hash]
###

@exec[filename;env_hash;*args]
###

@exec[format;filename;env_hash;*args]
###

@base64[encoded]
###

@base64[text;filename;encoded]
###

@base64[binary;filename;encoded]
###

@create[format;filename;text]
###

@create[format;filename;text;options]
###

@save[format;filename]
$result[^void::_has_no_constructor[]]
###

@save[format;filename;options]
$result[^void::_has_no_constructor[]]
###

@sql-string[]
$result[]
###

@base64[]
$result[]
###

@md5[]
$result[]
###

@crc32[]
$result[]
###

@static:delete[path]
$result[^void::_has_no_constructor[]]
###

@static:find[file]
$result[^void::_has_no_constructor[]]
###

@static:find[file;default]
$result[^void::_has_no_constructor[]]
###

@static:list[path]
$result[^table::create[]]
###

@static:list[path;filter]
$result[^table::create[]]
###

@static:copy[source;dest]
$result[^void::_has_no_constructor[]]
###

@static:move[source;dest]
$result[^void::_has_no_constructor[]]
###

@static:lock[lockfile;code]
$result[^void::_has_no_constructor[]]
###

@static:dirname[filename]
$result[]
###

@static:basename[filename]
$result[]
###

@static:justname[filename]
$result[]
###

@static:justext[filename]
$result[]
###

@static:fullpath[filename]
$result[]
###

@static:base64[filename]
$result[]
###

@static:md5[filename]
$result[]
###

@static:crc32[filename]
$result[]
###