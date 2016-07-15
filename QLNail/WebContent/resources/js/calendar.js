$(function () {
    var html = '<div class="calendarMonth"><ul><li class="calendarPrev" onclick="prevMonth()"><i class="fa fa-chevron-left" aria-hidden="true"></i></li><li class="calendarNext" onclick="nextMonth()"><i class="fa fa-chevron-right" aria-hidden="true"></i></li><li class="calendarTitle"><span style="font-size:18px">August</span> <span style="font-size:18px">2016</span></li></ul></div><ul class="calendarWeekdays"><li>Su</li><li>Mo</li><li>Tu</li><li>We</li><li>Th</li><li>Fr</li><li>Sa</li></ul><ul class="calendarDays"></ul>'
    $('#calendar').html(html);
    calendarFillDays();
});

function calendarFillDays() {

    var month = getMonthFromName($('#calendar .calendarTitle span:first').text());
    var year = +$('#calendar .calendarTitle span:nth-child(2)').text();

    var firstDayOfMonth = (new Date(year, month, 1)).getDay();
    var htmlDayFill = "";

    // fill old
    var maxDayPrevMonth = getPrevMonthDays(month, year);
    for (var i = firstDayOfMonth - 1; i >= 0; i--)
        htmlDayFill += '<li class="calendarInactive">' + (maxDayPrevMonth - i) + '</li>';

    // Fild current
    var maxDaysMonth;
    if (month == 11)
        maxDaysMonth = getPrevMonthDays(0, year + 1);
    else
        maxDaysMonth = getPrevMonthDays(month + 1, year);

    for (var i = 1; i <= maxDaysMonth; ++i)
        htmlDayFill += '<li class="calendarActive">' + (i < 10 ? '0' + i : i) + '</li>';

    // Fill next
    var startDayNextMonth = (new Date(year, month, maxDaysMonth)).getDay() + 1;
    for (var i = startDayNextMonth; i <= 6; ++i)
        htmlDayFill += '<li class="calendarInactive">' + '0' + (i - startDayNextMonth + 1) + '</li>';

    $('#calendar .calendarDays').html(htmlDayFill);
}

function getMonthFromName(monthName) {
    var months = ["January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"];
    for (var i = 0; i < months.length; i++)
        if (months[i] == monthName)
            return i;
}

function getMonthName(month) {
    var months = ["January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"];
    return months[month];
}

function getPrevMonthDays(month, year) {
    return new Date(year, month, 0).getDate();
}

function nextMonth() {
    var month = getMonthFromName($('#calendar .calendarTitle span:first').text());
    var year = +$('#calendar .calendarTitle span:nth-child(2)').text();
    if (month == 11) {
        $('#calendar .calendarTitle span:first').text(getMonthName(0));
        $('#calendar .calendarTitle span:nth-child(2)').text(year + 1);
    } else {
        $('#calendar .calendarTitle span:first').text(getMonthName(month + 1));
        $('#calendar .calendarTitle span:nth-child(2)').text(year);
    }
    calendarFillDays();
}

function prevMonth() {
    var month = getMonthFromName($('#calendar .calendarTitle span:first').text());
    var year = +$('#calendar .calendarTitle span:nth-child(2)').text();
    if (month == 0) {
        $('#calendar .calendarTitle span:first').text(getMonthName(11));
        $('#calendar .calendarTitle span:nth-child(2)').text(year - 1);
    } else {
        $('#calendar .calendarTitle span:first').text(getMonthName(month - 1));
        $('#calendar .calendarTitle span:nth-child(2)').text(year);
    }
    calendarFillDays();
}