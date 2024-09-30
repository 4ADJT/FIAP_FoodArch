export function createBDate(inicialYear = 1970, endYear = 2000) {
    const initial = new Date(inicialYear, 0, 1);
    const end = new Date(endYear, 11, 31);
    const date = new Date(initial.getTime() + Math.random() * (end.getTime() - initial.getTime()));

    const year = date.getFullYear();
    const month = (`0${date.getMonth() + 1}`).slice(-2);
    const newDate = (`0${date.getDate()}`).slice(-2);

    return `${year}-${month}-${newDate}`;
}
