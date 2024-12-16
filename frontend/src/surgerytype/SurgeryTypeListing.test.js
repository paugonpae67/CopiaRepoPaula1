import { render, screen } from "../test-utils";
import userEvent from "@testing-library/user-event";
import  SurgeryTypeListing from "./index";

describe('SurgeryTypeListing', () => {
    test('renders surgery type names correctly (as table cells)', async () => {
        render(<SurgeryTypeListing />);
        const dental = await screen.findByRole('cell', { 'name': 'Dental extractions' });
        expect(dental).toBeInTheDocument();

        const bladder = await screen.findByRole('cell', { 'name': 'Bladder surgery' });
        expect(bladder).toBeInTheDocument();
    });

    test('renders surgery type PetTypes names correctly (as items in a list)', async () => {
        render(<SurgeryTypeListing />);
        const dental = await screen.findByRole('cell', { 'name': 'Dental extractions' });
        const listitems=await screen.getAllByRole('listitem');
        const dog = listitems.find(listitem => listitem.textContent === 'dog')
        expect(dog).toBeInTheDocument();

        const cat = listitems.find(listitem => listitem.textContent === 'cat')
        expect(cat).toBeInTheDocument();

        const snake = listitems.find(listitem => listitem.textContent === 'snake')
        expect(snake).toBeInTheDocument();

    });

});