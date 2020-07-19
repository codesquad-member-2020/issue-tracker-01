export const getRelativeTime = (actionTime) => {
  const currDate = new Date();
  const diffMs = currDate.getTime() - new Date(actionTime).getTime();

  const second = diffMs / 1000;
  if (second < 60) return parseInt(second.toString()) + " second" + (second > 1 ? "s" : "");

  const minute = second / 60;
  if (minute < 60) return parseInt(minute.toString()) + " minute" + (minute > 1 ? "s" : "");

  const hour = minute / 60;
  if (hour < 24) return parseInt(hour.toString()) + " hour" + (hour > 1 ? "s" : "");

  const day = hour / 24;
  if (day < 30) return parseInt(day.toString()) + " day" + (day > 1 ? "s" : "");

  const month = day / 30;
  if (month < 12) return parseInt(month.toString()) + " month" + (month > 1 ? "s" : "");

  const year = month / 12;
  return parseInt(year.toString()) + " year" + (year > 1 ? "s" : "");
};
