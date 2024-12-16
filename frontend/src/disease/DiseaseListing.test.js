import { render, screen } from "../test-utils";
import userEvent from "@testing-library/user-event";
import  DiseasesListing from "./index";

describe('DiseasesListing', () => {
    test('renders dieases names correctly (as table cells)', async () => {
        render(<DiseasesListing />);
        const covid = await screen.findByRole('cell', { 'name': 'Covid' });
        expect(covid).toBeInTheDocument();

        const flu = await screen.findByRole('cell', { 'name': 'Flu' });
        expect(flu).toBeInTheDocument();
    });

    test('renders diseases PetTypes names correctly (as items in a list)', async () => {
        render(<DiseasesListing />);
        const covid = await screen.findByRole('cell', { 'name': 'Covid' });
        const listitems=await screen.getAllByRole('listitem');
        const dog = listitems.find(listitem => listitem.textContent === 'dog')
        expect(dog).toBeInTheDocument();

        const cat = listitems.find(listitem => listitem.textContent === 'cat')
        expect(cat).toBeInTheDocument();
    });

});