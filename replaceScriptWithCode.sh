#!/bin/bash

# Set the base directory from which to start searching
BASE_DIR="src/website/content"

# Check if the BASE_DIR exists
if [ ! -d "$BASE_DIR" ]; then
    echo "Error: Directory $BASE_DIR does not exist."
    exit 1
fi

# Find all files recursively and process each one
find "$BASE_DIR" -type f -exec grep -Il '<script src="https://gist-it.appspot.com/' {} + | while read -r file; do
    # Read the content of the file
    content=$(<"$file")

    # Use sed to replace the script tags with the desired Hugo shortcode format
    modified_content=$(echo "$content" | sed -E 's|<script src="https://gist-it.appspot.com/https://github.com/http4k/http4k/blob/master/src/docs/howto/(.*/)?([^/]+)"></script>|{{< kotlin file="\2" >}}|g')

    # Write the modified content back to the file if there are changes
    if [ "$content" != "$modified_content" ]; then
        echo "$modified_content" > "$file"
        echo "Modified: $file"
    fi
done

echo "Replacement complete!"