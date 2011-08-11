#!/bin/bash
#
# Author: Alexandre Normand (https://github.com/alexandre-normand)
# Date: July 30th, 2011
#
# Many variables (including source paths) can/must be overriden.
# Refer to package.sh for the list

SOURCE_PATH_PARSER_PLUGIN=${SOURCE_PATH_PARSER_PLUGIN:-`pwd`/..}
TARGET_HOST=darwin ./package.sh

function createDmg()
{
    echo
    echo "Creating dmg image from ${SOURCE_PATH_PARSER_PLUGIN}/dist/darwin_$1"
    echo

    rm -r ${SOURCE_PATH_PARSER_PLUGIN}/dist/darwin_$1
    unzip -o ${SOURCE_PATH_PARSER_PLUGIN}/dist/parseride-darwin_$1.zip -d ${SOURCE_PATH_PARSER_PLUGIN}/dist/darwin_$1
    mv -f "${SOURCE_PATH_PARSER_PLUGIN}/dist/darwin_$1/parseride" "${SOURCE_PATH_PARSER_PLUGIN}/dist/darwin_$1/Parser Ide.app"
    SRC_FOLDER="${SOURCE_PATH_PARSER_PLUGIN}/dist/darwin_$1" IMAGE_FILE=${SOURCE_PATH_PARSER_PLUGIN}/dist/parseride_osx_$1.dmg ./make_dmg.sh
}

createDmg 386
createDmg amd64