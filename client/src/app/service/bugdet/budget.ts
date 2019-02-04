import {Category} from '../category/category';

export class Budget {
    budgetId: number;
    name: String;
    startDate: Date;
    endDate: Date;
    expenditureLimit: number;
    categories: Category[];
}
