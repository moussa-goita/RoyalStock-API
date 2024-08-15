import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EntrepotPage } from './entrepot.page';

describe('EntrepotPage', () => {
  let component: EntrepotPage;
  let fixture: ComponentFixture<EntrepotPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(EntrepotPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
