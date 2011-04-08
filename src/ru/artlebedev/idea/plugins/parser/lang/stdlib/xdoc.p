@CLASS
xdoc

@create[xmlcode]
###

@create[basepath;xmlcode]
###

@create[tagname]
###

@create[basepath;tagname]
###

@create[file]
###

@load[filename]
###

@createElement[tagName]
$result[^xnode::create[]]
###

@createDocumentFragment[]
$result[^xnode::create[]]
###

@createTextNode[data]
$result[^xnode::create[]]
###

@createComment[data]
$result[^xnode::create[]]
###

@createCDATASection[data]
$result[^xnode::create[]]
###

@createProcessingInstruction[target;data]
$result[^xnode::create[]]
###

@createAttribute[name]
$result[^xnode::create[]]
###

@createEntityReference[name]
$result[^xnode::create[]]
###

@getElementsByTagName[tagname]
$result[^hash::create[]]
###

@importNode[importedNode;deep]
$result[^xnode::create[]]
###

@createElementNS[namespaceURI;qualifiedName]
$result[^xnode::create[]]
###

@createAttributeNS[namespaceURI;qualifiedName]
$result[^xnode::create[]]
###

@getElementsByTagNameNS[namespaceURI;localName]
$result[^hash::create[]]
###

@getElementById[elementId]
$result[^xnode::create[]]
###