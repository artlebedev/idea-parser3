@CLASS
hash

@create[]
###

@sql[]
###

#:dynamic
@_keys[]
$result[^table::create[]]
###

#:dynamic
@_keys[column]
$result[^table::create[]]
###

#:dynamic
@_count[]
$result(1)
###

#:dynamic
@_at[number]
$result[]
###

#:dynamic
@foreach[key;value;body]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@foreach[key;value;body;splitter]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@delete[]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@contains[key]
$result[^bool::_has_no_constructor_[]]
###

#:dynamic
@sub[hash]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@add[hash]
$result[^void::_has_no_constructor_[]]
###

#:dynamic
@union[hash]
$result[^hash::create[]]
###

#:dynamic
@intersection[hash]
$result[^hash::create[]]
###

#:dynamic
@intersects[hash]
$result[^bool::_has_no_constructor_[]]
###