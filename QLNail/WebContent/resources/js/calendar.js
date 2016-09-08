
var cal_hours;
	
	var cal_data_cols;
	var cal_data_display; // contains only show data_cols
	var cal_timeWidth = 50;
	var cal_timeHeight = 22;
	var cal_numTimeBlock = 4;
	var cal_headerHeight = 22;
	var cal_navHeight = 70;
	var cal_dataWidth = 0;
	var cal_dataRemainingWidth = 0;
	var cal_data_col_min_width = 300;
	var cal_data_col_display = 0; // store number data col display
	var cal_data_col_start_ind = 0;

	$(function() {
		// testing data
		
		cal_data_cols = JSON.parse(cal_data_cols_JSON);
		// get all display hours
		cal_hours = [];
		for ( var i = cal_hour_start; i <= cal_hour_end; i++) {
			cal_hours.push(i >= 12 ? (i > 12 ? (i - 12) : i) + "PM" : i + "AM");
		}
		// Set nav height
		cal_navHeight = parseFloat($('.cal_navline').css("height"));

		fillCalendar();
	});
	// function be called at the end of resize
	var resizeTimer;
	$(window).on('resize', function(e) {
		clearTimeout(resizeTimer);
		resizeTimer = setTimeout(function() {
			fillCalendar();
		}, 250);
	});
	// Update calendar after every 1 minute
	setInterval(function() {
		updateCurrentTimeLine();
	}, 60000);
	function fillCalendar() {
		var w = $(window).width();
		// cal number of data column display
		var dataWidth = w - cal_timeWidth;
		cal_data_col_display = Math.floor(dataWidth / cal_data_col_min_width);
		cal_data_col_display = Math.min(cal_data_cols.length, cal_data_col_display);
		cal_dataWidth = Math.floor(dataWidth / cal_data_col_display) - 2;
		// cal remaining width to fix data col gap
		cal_dataRemainingWidth = dataWidth - cal_dataWidth
				* (cal_data_col_display - 1);
		// get all display info
		cal_data_display = [];
		$(cal_data_cols).each(
				function(index, value) {
					if (index >= cal_data_col_start_ind
					&& index < cal_data_col_start_ind
									+ cal_data_col_display) {
						cal_data_display.push(value);
					}
				});
		$('.cal_data, .cal_header').css("width", w);
		$('.cal_data, .cal_header').html("");
		addHeader();
		addTime();
		addColumn();
		fillTimeOff();
		addEvent();
		calendarApplyTheme();
	}
	function addHeader() {
		var header = $('.cal_header');
		var timeDiv = document.createElement('div');
		$(timeDiv).addClass('cal_header_col');
		$(timeDiv).css({
			"width" : cal_timeWidth,
			"height" : cal_headerHeight
		});
		$(timeDiv).html("&nbsp;");
		$(header).append(timeDiv);
		$(cal_data_display).each(function(index, value) {
			var colDiv = document.createElement('div');
			$(colDiv).attr("id", "cal_header_col_" + index);
			$(colDiv).addClass('cal_header_col');
			$(colDiv).css({
				"width" : cal_dataWidth,
				"height" : cal_headerHeight
			});
			$(colDiv).text(Object.keys(value));
			$(header).append(colDiv);
		});
		// Left Nav Btn
		var cal_header_left_nav = document.createElement('div');
		$(cal_header_left_nav).addClass('cal_header_left_nav');
		$(cal_header_left_nav).css({
			"height" : cal_headerHeight,
			"left" : cal_timeWidth
		});
		if (cal_data_col_start_ind > 0) {
			// Enable btn
			$(cal_header_left_nav).addClass("cal_header_nav_enable");
			$(cal_header_left_nav).on("click", function() {
				cal_data_col_start_ind -= 1;
				fillCalendar();
			});
		} else {
			// Disable btn
			$(cal_header_left_nav).addClass("cal_header_nav_disable");
		}
		$(header).append(cal_header_left_nav);

		// Right Nav Btn
		var cal_header_right_nav = document.createElement('div');
		$(cal_header_right_nav).addClass('cal_header_right_nav');
		$(cal_header_right_nav).css({
			"height" : cal_headerHeight,
			"right" : "0px"
		});
		if (cal_data_col_start_ind + cal_data_col_display < cal_data_cols.length) {
			// Enable btn
			$(cal_header_right_nav).addClass("cal_header_nav_enable");
			$(cal_header_right_nav).on("click", function() {
				cal_data_col_start_ind += 1;
				fillCalendar();
			});
		} else {
			// Disable btn
			$(cal_header_right_nav).addClass("cal_header_nav_disable");
		}

		$(header).append(cal_header_right_nav);
	}
	function addTime() {
		var timeDiv = document.createElement('div');
		$(timeDiv).addClass('cal_time cal_data_col');
		$(timeDiv).css({
			"width" : cal_timeWidth,
			"height" : cal_timeHeight * cal_hours.length,
			"top" : cal_headerHeight + cal_navHeight
		});
		$(cal_hours).each(function(index, value) {
			var timeBlockDiv = document.createElement('div');
			$(timeBlockDiv).css("height", cal_timeHeight * cal_numTimeBlock);
			$(timeBlockDiv).addClass('cal_data_cell');
			$(timeBlockDiv).text(value);
			$(timeDiv).append(timeBlockDiv);
		});
		$('.cal_data').append(timeDiv);
	}
	function addColumn() {
		$(cal_data_display).each(function(index, value) {
			var colDiv = document.createElement('div');
			$(colDiv).attr("id", "cal_data_col_" + index);
			$(colDiv).addClass('cal_data_col');
			$(colDiv).css({
				"width" : cal_dataWidth,
				"height" : cal_timeHeight * cal_hours.length,
				"top" : cal_headerHeight + cal_navHeight
			});
			$(cal_hours).each(function(index, value) {
				for( var i = 0; i < cal_numTimeBlock; ++i) {
					var timeBlockDiv = document.createElement('div');
					$(timeBlockDiv).addClass('cal_data_cell');
					$(timeBlockDiv).css("height", cal_timeHeight);
					$(colDiv).append(timeBlockDiv);
				}
			});
			$('.cal_data').append(colDiv);

		});
		// fix data col gap
		$('.cal_data_col').last().css("width", cal_dataRemainingWidth - 2);
		// Add current time line
		var lineHr = document.createElement('div');
		$(lineHr).addClass('cal_time_line');
		$(lineHr).css({
			"border" : "solid 1px red",
			"width" : "100%"
		});
		$('.cal_data').append(lineHr);
		updateCurrentTimeLine();
	}
	function fillTimeOff() {
		$(cal_data_display).each(function(index, value) {
			//$(colDiv).attr("id", "cal_header_col_" + index);
			var staff = value[Object.keys(value)];
			$('#cal_data_col_'+index + ' .cal_data_cell').eq((staff['startHour']-cal_hour_start)*cal_numTimeBlock).prevAll().addClass('cal_data_cell_off');
			$('#cal_data_col_'+index + ' .cal_data_cell').eq((staff['endHour']-cal_hour_start)*cal_numTimeBlock-1).nextAll().addClass('cal_data_cell_off');
			console.log(value);
		});
	}
	// Limitation: cut off all out range event
	function addEvent() {
		$(cal_data_display).each(
				function(personIndex, personEvents) {
					$(personEvents[Object.keys(personEvents)]['events']).each(
							function(eventIndex, eventValue) {
								var startTime = new Date(0, 0, 0,
										eventValue['startHour'],
										eventValue['startMinute'], 0);
								var endTime = new Date(0, 0, 0,
										eventValue['endHour'],
										eventValue['endMinute'], 0);
								var event = document.createElement('div');
								$(event).addClass('cal_event');
								$(event).css({
									"width" : "100%",
									"height" : getEventHeight(startTime, endTime),
									"top" : getEventTop(startTime)
								});
								$(event).html(eventValue['eventName']);

								$('#cal_data_col_' + personIndex).append(event);
							});
				});
	}
	function updateCurrentTimeLine() {
		var simulateTime = new Date();
		if (simulateTime.getHours() >= cal_hour_start
		&& simulateTime.getHours() <= cal_hour_end) {
			var currTimeTopPos = getEventTop(simulateTime) + cal_headerHeight
					+ cal_navHeight;
			var windowHeightHalf = $(window).height() / 2;
			if (currTimeTopPos > windowHeightHalf)
				$(window).scrollTop(currTimeTopPos - windowHeightHalf);
			else
				$(window).scrollTop(0);
			$('.cal_time_line').css("top", currTimeTopPos);
		}
	}
	/* Helper functions */
	function getEventTop(startTime) {
		var px = (startTime.getHours() - cal_hour_start) + startTime.getMinutes()
				/ 60;
		return px * cal_timeHeight * cal_numTimeBlock;
	}
	function getEventHeight(startTime, endTime) {
		var px = (endTime.getHours() - startTime.getHours())
				+ (endTime.getMinutes() - startTime.getMinutes()) / 60;
		return px * cal_timeHeight * cal_numTimeBlock;
	}