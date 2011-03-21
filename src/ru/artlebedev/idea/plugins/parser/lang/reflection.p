@CLASS
reflection

@static:create[classname;constructor]
###

@static:create[classname;constructor;*args]
###

@static:classes[]
$result[^hash::create[]]
###

@static:class[object]
$result[]
###

@static:class_name[object]
$result[]
###

@static:base[class]
###

@static:base_name[class]
$result[]
###

@static:base_name[object]
$result[]
###

@static:methods[classname]
$result[^hash::create[]]
###

@static:method_info[classname;methodname]
$result[^hash::create[]]
###

@static:fields[class]
$result[^hash::create[]]
###

@static:fields[object]
$result[^hash::create[]]
###

@static:dynamical[]
$result[^bool::_has_no_constructor_[]]
###

@static:dynamical[class]
$result[^bool::_has_no_constructor_[]]
###

@static:dynamical[object]
$result[^bool::_has_no_constructor_[]]
###

@static:copy[from;to]
$result[^void::_has_no_constructor_[]]
###