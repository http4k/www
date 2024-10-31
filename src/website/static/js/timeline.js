function formatQuarter(year) {
    const wholeYear = Math.floor(year);
    const quarter = Math.floor((year - wholeYear) * 4) + 1;
    return `${wholeYear} Q${quarter}`;
}

const data = {
    "Java": [
        {
            title: "11",
            segments: [
                {start: 2023, end: 2025, type: "Public", color: "#a8d5ff"},
                {start: 2025, end: 2030, type: "Commercial", color: "#ffe69c"}
            ]
        },
        {
            title: "17",
            segments: [
                {start: 2023, end: 2025, type: "Public", color: "#a8d5ff"},
                {start: 2025, end: 2030, type: "Commercial", color: "#ffe69c"}
            ]
        },
        {
            title: "21",
            segments: [
                {start: 2023.75, end: 2028, type: "Public", color: "#a8d5ff"},
                {start: 2027, end: 2031, type: "Commercial", color: "#ffe69c"}
            ]
        },
        {
            title: "25",
            segments: [
                {start: 2025.75, end: 2030, type: "Public", color: "#a8d5ff"},
                {start: 2029, end: 2031, type: "Commercial", color: "#ffe69c"}
            ]
        }
    ],
    "http4k LTS": [
        {
            title: "4.X (Java 8)",
            segments: [
                {start: 2023, end: 2023.25, type: "OSS", color: "#a8d5ff"},
                {start: 2023.25, end: 2027, type: "LTS", color: "#ffe69c"}
            ]
        },
        {
            title: "5.X (Java 8)",
            segments: [
                {start: 2023, end: 2023.75, type: "OSS", color: "#a8d5ff"},
                {start: 2023.75, end: 2027, type: "LTS", color: "#ffe69c"}
            ]
        },
        {
            title: "6.X (Java 21)",
            segments: [
                {start: 2025, end: 2027, type: "OSS", color: "#a8d5ff"},
                {start: 2027, end: 2029, type: "LTS", color: "#ffe69c"}
            ]
        },
        {
            title: "7.X (Java 25)",
            segments: [
                {start: 2027, end: 2029, type: "OSS", color: "#a8d5ff"},
                {start: 2029, end: 2031, type: "LTS", color: "#ffe69c"}
            ]
        },
    ]
}

function createTimeline(title, startYear = 2023, endYear = 2031) {
    const containerDiv = document.createElement('div');
    containerDiv.className = 'timeline-container';

    const headerDiv = document.createElement('div');
    headerDiv.className = 'timeline-header';
    headerDiv.textContent = title;
    containerDiv.appendChild(headerDiv);

    const markersDiv = document.createElement('div');
    markersDiv.className = 'year-markers';
    containerDiv.appendChild(markersDiv);

    const timelineDiv = document.createElement('div');
    timelineDiv.id = title;
    containerDiv.appendChild(timelineDiv);

    document.body.appendChild(containerDiv);

    const container = document.getElementById(title);
    container.innerHTML = '';

    // Calculate the total width including the final year
    const totalYears = endYear - startYear;

    // Create year markers and quarter markers
    for (let year = startYear; year <= endYear; year++) {
        // Add year marker at the start of each year
        const yearMarker = document.createElement('div');
        yearMarker.className = 'year-marker';
        const position = ((year - startYear) / totalYears) * 100;
        yearMarker.style.left = `${position}%`;

        // Add year label
        const yearLabel = document.createElement('div');
        yearLabel.className = 'year-label';
        yearLabel.textContent = year;
        yearMarker.appendChild(yearLabel);

        // Add year line
        const yearLine = document.createElement('div');
        yearLine.className = 'year-line';
        yearMarker.appendChild(yearLine);

        markersDiv.appendChild(yearMarker);

        // Add quarter markers if not the last year
        if (year < endYear) {
            for (let quarter = 1; quarter < 4; quarter++) {
                const quarterPosition = ((year - startYear + quarter * 0.25) / totalYears) * 100;

                const quarterMarker = document.createElement('div');
                quarterMarker.className = 'quarter-marker';
                quarterMarker.style.left = `${quarterPosition}%`;
                markersDiv.appendChild(quarterMarker);
            }
        }
    }

    // Create timeline rows
    data[title].forEach(row => {
        const timelineRow = document.createElement('div');
        timelineRow.className = 'timeline-row';

        const label = document.createElement('div');
        label.className = 'timeline-label';
        label.textContent = row.title;

        const chart = document.createElement('div');
        chart.className = 'timeline-chart';

        // Add light grid lines
        const timelineGrid = document.createElement('div');
        timelineGrid.className = 'timeline-grid';
        for (let year = startYear; year <= endYear; year++) {
            for (let quarter = 0; quarter < 4; quarter++) {
                if (year === endYear && quarter > 0) continue;
                const position = ((year - startYear + quarter * 0.25) / totalYears) * 100;
                const gridLine = document.createElement('div');
                gridLine.className = 'grid-line';
                gridLine.style.left = `${position}%`;
                timelineGrid.appendChild(gridLine);
            }
        }
        chart.appendChild(timelineGrid);

        // Add segments
        row.segments.forEach(segment => {
            const bar = document.createElement('div');
            bar.className = 'timeline-bar';

            // Calculate position and width with quarter precision
            const startPosition = ((segment.start - startYear) / totalYears) * 100;
            const width = ((segment.end - segment.start) / totalYears) * 100;

            bar.style.left = startPosition + '%';
            bar.style.width = width + '%';
            bar.style.backgroundColor = segment.color;

            // Add tooltip with quarter precision
            bar.title = `${segment.type}: ${formatQuarter(segment.start)} - ${formatQuarter(segment.end)}`;

            chart.appendChild(bar);
        });

        timelineRow.appendChild(label);
        timelineRow.appendChild(chart);
        container.appendChild(timelineRow);
    });
}
